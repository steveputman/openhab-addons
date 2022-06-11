/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.shelly.internal.api2;

import static org.openhab.binding.shelly.internal.ShellyBindingConstants.*;
import static org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.*;
import static org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.*;
import static org.openhab.binding.shelly.internal.handler.ShellyComponents.updateSensors;
import static org.openhab.binding.shelly.internal.util.ShellyUtils.*;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.http.HttpStatus;
import org.openhab.binding.shelly.internal.api.ShellyApiException;
import org.openhab.binding.shelly.internal.api.ShellyApiInterface;
import org.openhab.binding.shelly.internal.api.ShellyApiResult;
import org.openhab.binding.shelly.internal.api.ShellyDeviceProfile;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyInputState;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyOtaCheckResult;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyRollerStatus;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySensorSleepMode;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySettingsDevice;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySettingsEMeter;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySettingsLogin;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySettingsMeter;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySettingsRelay;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellySettingsStatus;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyShortLightStatus;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyShortStatusRelay;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyStatusLight;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyStatusRelay;
import org.openhab.binding.shelly.internal.api1.Shelly1ApiJsonDTO.ShellyStatusSensor;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2AuthResponse;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2DeviceConfig.Shelly2GetConfigResult;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2DeviceSettings;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2DeviceStatus.Shelly2DeviceStatusResult;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2DeviceStatus.Shelly2DeviceStatusSys.Shelly2DeviceStatusSysAvlUpdate;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2NotifyEvent;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2RpcBaseMessage;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2RpcNotifyEvent;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2RpcNotifyStatus;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2RpcNotifyStatus.Shelly2NotifyStatus;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2RpcRequest;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2WsConfig;
import org.openhab.binding.shelly.internal.api2.Shelly2ApiJsonDTO.Shelly2WsConfigResponse;
import org.openhab.binding.shelly.internal.config.ShellyThingConfiguration;
import org.openhab.binding.shelly.internal.handler.ShellyBaseHandler;
import org.openhab.binding.shelly.internal.handler.ShellyThingInterface;
import org.openhab.core.library.unit.SIUnits;
import org.openhab.core.thing.ThingStatusDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Shelly2ApiRpc} implements Gen2 RPC interface
 *
 * @author Markus Michels - Initial contribution
 */
@NonNullByDefault
public class Shelly2ApiRpc extends Shelly2ApiClient implements ShellyApiInterface, Shelly2WebSocketInterface {
    private final Logger logger = LoggerFactory.getLogger(Shelly2ApiRpc.class);
    private final Shelly2WebSocket rpcSocket;

    private boolean initialized = false;
    private Shelly2AuthResponse authInfo = new Shelly2AuthResponse();

    /**
     * Regular constructor - called by Thing handler
     *
     * @param thingName Symbolic thing name
     * @param thing Thing Handler (ThingHandlerInterface)
     */
    public Shelly2ApiRpc(String thingName, ShellyThingInterface thing) {
        super(thingName, thing);
        this.thing = thing;
        try {
            getProfile().initFromThingType(thingName);
        } catch (ShellyApiException e) {
            logger.info("{}: Shelly2 API initialization failed!", thingName, e);
        }

        rpcSocket = new Shelly2WebSocket(config.deviceIp);
        rpcSocket.addMessageHandler(this);
    }

    /**
     * Simple initialization - called by discovery handler
     *
     * @param thingName Symbolic thing name
     * @param config Thing Configuration
     * @param httpClient HTTP Client to be passed to ShellyHttpClient
     */
    public Shelly2ApiRpc(String thingName, ShellyThingConfiguration config, HttpClient httpClient) {
        super(thingName, config, httpClient);
        rpcSocket = new Shelly2WebSocket(config.deviceIp);
        rpcSocket.addMessageHandler(this);
    }

    @Override
    public void initialize() throws ShellyApiException {
        if (thing != null && !rpcSocket.isConnected()) {
            rpcSocket.connect();
        }
        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public ShellyDeviceProfile getDeviceProfile(String thingType) throws ShellyApiException {
        initialize();

        ShellyDeviceProfile profile = thing != null ? getProfile() : new ShellyDeviceProfile();
        Shelly2GetConfigResult dc = apiRequest(SHELLYRPC_METHOD_GETCONFIG, null, Shelly2GetConfigResult.class);
        profile.thingName = thingName;
        profile.settings.name = profile.status.name = dc.sys.device.name;
        profile.name = getString(profile.settings.name);
        profile.settings.timezone = getString(dc.sys.location.tz);
        if (dc.cloud != null) {
            profile.settings.cloud.enabled = getBool(dc.cloud.enable);
        }
        if (dc.mqtt != null) {
            profile.settings.mqtt.enable = getBool(dc.mqtt.enable);
        }
        if (dc.sys.sntp != null) {
            profile.settings.sntp.server = dc.sys.sntp.server;
        }

        profile.isRoller = dc.cover0 != null; // SHELLY2_PROFILE_ROLLER.equalsIgnoreCase(getString(dc.sys.device.profile));
        profile.settings.relays = fillRelaySettings(profile, dc);
        profile.settings.inputs = fillInputSettings(profile, dc);
        profile.settings.rollers = fillRollerSettings(profile, dc);

        profile.isEMeter = true;
        profile.numInputs = profile.settings.inputs != null ? profile.settings.inputs.size() : 0;
        profile.numRelays = profile.settings.relays != null ? profile.settings.relays.size() : 0;
        profile.numRollers = profile.settings.rollers != null ? profile.settings.rollers.size() : 0;
        profile.hasRelays = profile.numRelays > 0 || profile.numRollers > 0;
        profile.mode = "";
        if (profile.hasRelays) {
            profile.mode = profile.isRoller ? SHELLY_CLASS_ROLLER : SHELLY_CLASS_RELAY;
        }

        ShellySettingsDevice device = getDeviceInfo();
        profile.settings.device = device;
        profile.hostname = device.hostname;
        profile.deviceType = device.type;
        profile.mac = device.mac;
        profile.auth = device.auth;

        profile.fwDate = substringBefore(device.fw, "/");
        profile.fwVersion = substringBefore(ShellyDeviceProfile.extractFwVersion(device.fw.replace("/", "/v")), "-");
        profile.status.update.oldVersion = profile.fwVersion;
        profile.status.hasUpdate = profile.status.update.hasUpdate = false;

        if (profile.hasRelays) {
            profile.status.relays = new ArrayList<>();
            profile.status.meters = new ArrayList<>();
            profile.status.emeters = new ArrayList<>();
            relayStatus.relays = new ArrayList<>();
            relayStatus.meters = new ArrayList<>();
            profile.numMeters = profile.isRoller ? profile.numRollers : profile.numRelays;
            for (int i = 0; i < profile.numMeters; i++) {
                profile.status.relays.add(new ShellySettingsRelay());
                profile.status.meters.add(new ShellySettingsMeter());
                profile.status.emeters.add(new ShellySettingsEMeter());
                relayStatus.relays.add(new ShellyShortStatusRelay());
                relayStatus.meters.add(new ShellySettingsMeter());
            }
        }

        profile.status.inputs = new ArrayList<>();
        for (int i = 0; i < profile.numInputs; i++) {
            ShellyInputState input = new ShellyInputState();
            input.input = 0;
            input.event = "";
            input.eventCount = 0;
            profile.status.inputs.add(input);
        }
        relayStatus.inputs = profile.status.inputs;

        profile.status.rollers = new ArrayList<>();
        for (int i = 0; i < profile.numRollers; i++) {
            ShellyRollerStatus rs = new ShellyRollerStatus();
            profile.status.rollers.add(rs);
            rollerStatus.add(rs);
        }

        profile.status.dimmers = profile.isDimmer ? new ArrayList<>() : null;
        profile.status.lights = profile.isBulb ? new ArrayList<>() : null;
        profile.status.thermostats = profile.isTRV ? new ArrayList<>() : null;

        // request status update, this triggers WebSocket updates
        asyncApiRequest(SHELLYRPC_METHOD_GETSTATUS);

        if (profile.hasBattery) {
            profile.settings.sleepMode = new ShellySensorSleepMode();
            profile.settings.sleepMode.unit = "m";
            profile.settings.sleepMode.period = dc.sys.sleep != null ? dc.sys.sleep.wakeupPeriod / 60 : 720;
            checkSetWsCallback();
        }

        profile.initialized = true;
        return profile;
    }

    private void checkSetWsCallback() throws ShellyApiException {
        // Shelly2WsConfig wsConfig = callApi("/rpc/" + SHELLYRPC_METHOD_WSGETCONFIG, Shelly2WsConfig.class);
        Shelly2WsConfig wsConfig = apiRequest(SHELLYRPC_METHOD_WSGETCONFIG, null, Shelly2WsConfig.class);
        ShellyThingConfiguration config = getThing().getThingConfig();
        String url = "ws://" + config.localIp + ":" + config.localPort + "/shelly/wsevent";
        if (!getBool(wsConfig.enable) || !url.equalsIgnoreCase(getString(wsConfig.server))) {
            logger.debug("{}: A battery device was detected without correct callback, fix it", thingName);
            wsConfig.enable = true;
            wsConfig.server = url;
            Shelly2RpcRequest request = new Shelly2RpcRequest();
            request.id = 0;
            request.method = SHELLYRPC_METHOD_WSSETCONFIG;
            request.params.config = wsConfig;
            // Shelly2WsConfigResponse response = postApi("/rpc/", gson.toJson(request), Shelly2WsConfigResponse.class);
            Shelly2WsConfigResponse response = apiRequest(SHELLYRPC_METHOD_WSSETCONFIG, request.params,
                    Shelly2WsConfigResponse.class);
            if (response.result.restartRequired) {
                logger.info("{}: WebSocket callback was updated, device is restarting", thingName);
                getThing().getApi().deviceReboot();
                getThing().reinitializeThing();
            }
        }
    }

    @Override
    public void onConnect(boolean connected) {
        logger.debug("{}: WebSocket {}", thingName, connected ? "connected successful" : "failed to connect!");
    }

    @Override
    public void onNotifyStatus(Shelly2RpcNotifyStatus message) {
        logger.debug("{}: NotifyStatus update received: {}", thingName, gson.toJson(message));
        try {
            if (thing == null) {
                logger.trace("{}: No matching thing, ignore", thingName);
                return;
            }
            if (message.error != null) {
                logger.debug("{}: Error status received - {} {}", thingName, message.error.code, message.error.message);
                if (message.error.code == HttpStatus.UNAUTHORIZED_401 && !getString(message.error.message).isEmpty()) {
                    // Save nonce for notification
                    Shelly2AuthResponse auth = gson.fromJson(message.error.message, Shelly2AuthResponse.class);
                    if (auth != null) {
                        logger.debug("{}: Authentication data received: {}", thingName, message.error.message);
                        authInfo = auth;
                    }
                }
            }

            Shelly2NotifyStatus params = message.params;
            if (params != null) {
                boolean updated = false;
                ShellyDeviceProfile profile = getProfile();
                ShellySettingsStatus status = profile.status;
                if (params.sys != null) {
                    if (params.sys.restartRequired) {
                        logger.warn("{}: Device requires restart to activate changes", thingName);
                    }
                    status.uptime = getLong(params.sys.uptime);
                }

                status.temperature = SHELLY_APU_INVTEMP; // mark invalid
                updated |= updateRelayStatus(status, params.switch0);
                updated |= updateRelayStatus(status, params.switch1);
                updated |= updateRelayStatus(status, params.switch2);
                updated |= updateRelayStatus(status, params.switch3);
                updated |= updateInputStatus(status, params, true);
                updated |= fillRollerStatus(status, params.cover0);

                updateHumidityStatus(sensorData, params.humidity0);
                updateTemperatureStatus(sensorData, params.temperature0);
                updateBatteryStatus(sensorData, params.devicepower0);

                updated |= updateSensors((ShellyBaseHandler) getThing(), status);

                if (status.temperature == SHELLY_APU_INVTEMP) {
                    // no device temp available
                    status.temperature = null;
                } else {
                    updated |= updateChannel(CHANNEL_GROUP_DEV_STATUS, CHANNEL_DEVST_ITEMP,
                            toQuantityType(getDouble(status.tmp.tC), DIGITS_NONE, SIUnits.CELSIUS));
                }

                profile.status = status;
                if (updated) {
                    getThing().restartWatchdog();
                }
            }
        } catch (ShellyApiException e) {
            logger.debug("{}: Unable to process status update", thingName, e);
        }
    }

    @Override
    public void onNotifyEvent(Shelly2RpcNotifyEvent message) {
        try {
            logger.debug("{}: NotifyEvent  received: {}", thingName, gson.toJson(message));
            ShellyDeviceProfile profile = getProfile();

            getThing().restartWatchdog();

            for (Shelly2NotifyEvent e : message.params.events) {
                switch (e.event) {
                    case SHELLY2_EVENT_BTNUP:
                    case SHELLY2_EVENT_BTNDOWN:
                        String bgroup = getProfile().getInputGroup(e.id);
                        updateChannel(bgroup, CHANNEL_INPUT + profile.getInputSuffix(e.id),
                                getOnOff(SHELLY2_EVENT_BTNDOWN.equals(getString(e.event))));
                        getThing().triggerButton(profile.getInputGroup(e.id), e.id,
                                mapValue(MAP_INPUT_EVENT_ID, e.event));
                        break;

                    case SHELLY2_EVENT_1PUSH:
                    case SHELLY2_EVENT_2PUSH:
                    case SHELLY2_EVENT_3PUSH:
                    case SHELLY2_EVENT_LPUSH:
                    case SHELLY2_EVENT_SLPUSH:
                    case SHELLY2_EVENT_LSPUSH:
                        ShellyInputState input = profile.status.inputs.get(e.id);
                        input.event = getString(MAP_INPUT_EVENT_TYPE.get(e.event));
                        input.eventCount = getInteger(input.eventCount) + 1;
                        profile.status.inputs.set(e.id, input);
                        relayStatus.inputs.set(e.id, input);

                        String group = getProfile().getInputGroup(e.id);
                        updateChannel(group, CHANNEL_STATUS_EVENTTYPE + profile.getInputSuffix(e.id),
                                getStringType(input.event));
                        updateChannel(group, CHANNEL_STATUS_EVENTCOUNT + profile.getInputSuffix(e.id),
                                getDecimal(input.eventCount));
                        getThing().triggerButton(profile.getInputGroup(e.id), e.id,
                                mapValue(MAP_INPUT_EVENT_ID, e.event));
                        break;
                    case SHELLY2_EVENT_CFGCHANGED:
                        logger.debug("{}: Configuration update detected, re-initialize", thingName);
                        getThing().requestUpdates(1, true); // refresh config
                        break;

                    case SHELLY2_EVENT_OTASTART:
                        getThing().postEvent(e.event, true);
                        getThing().setThingOffline(ThingStatusDetail.FIRMWARE_UPDATING,
                                "offline.status-error-fwupgrade");
                        break;
                    case SHELLY2_EVENT_OTAPROGRESS:
                        getThing().postEvent(e.event, false);
                        break;
                    case SHELLY2_EVENT_OTADONE:
                        getThing().setThingOffline(ThingStatusDetail.CONFIGURATION_PENDING,
                                "offline.status-error-restarted");
                        getThing().requestUpdates(1, true); // refresh config
                        break;
                    case SHELLY2_EVENT_SLEEP:
                        logger.debug("{}: Device went to sleep mode", thingName);
                        break;

                    default:
                        logger.debug("{}: Event {} was not handled", thingName, e.event);
                }
            }
        } catch (ShellyApiException e) {
            logger.debug("{}: Unable to process event", thingName, e);
        }
    }

    @Override
    public void onMessage(String message) {
        logger.debug("{}: RPC message received: {}", thingName, message);
    }

    @Override
    public void onClose() {
        logger.debug("{}: RPC connection closed", thingName);
    }

    @Override
    public void onError(Throwable cause) {
        try {
            logger.debug("{}: WebSocket error, reinit thing", thingName, cause);
            getThing().setThingOffline(ThingStatusDetail.COMMUNICATION_ERROR, "offline.status-error-unexpected-error");
            getThing().reinitializeThing();
        } catch (ShellyApiException e) {
        }
    }

    @Override
    public ShellySettingsDevice getDeviceInfo() throws ShellyApiException {
        Shelly2DeviceSettings device = callApi("/shelly", Shelly2DeviceSettings.class);
        ShellySettingsDevice info = new ShellySettingsDevice();
        info.hostname = getString(device.id);
        info.fw = getString(device.firmware);
        info.type = getString(device.model);
        info.mac = getString(device.mac);
        info.auth = getBool(device.authEnable);
        return info;
    }

    @Override
    public ShellySettingsStatus getStatus() throws ShellyApiException {
        Shelly2DeviceStatusResult ds = apiRequest(SHELLYRPC_METHOD_GETSTATUS, null, Shelly2DeviceStatusResult.class);
        ShellySettingsStatus status = getProfile().status;
        status.time = getString(ds.sys.time);
        status.cloud.connected = getBool(ds.cloud.connected);
        status.mqtt.connected = getBool(ds.mqtt.connected);
        status.wifiSta.ssid = getString(ds.wifi.ssid);
        status.wifiSta.ip = getString(ds.wifi.staIP);
        status.wifiSta.rssi = getInteger(ds.wifi.rssi);
        status.fsFree = ds.sys.fsFree;
        status.fsSize = ds.sys.fsSize;
        status.discoverable = true;
        status.hasUpdate = status.update.hasUpdate = false;
        status.update.oldVersion = getProfile().fwVersion;
        if (ds.sys.availableUpdates != null) {
            status.update.hasUpdate = ds.sys.availableUpdates.stable != null;
            if (ds.sys.availableUpdates.stable != null) {
                status.update.newVersion = "v" + getString(ds.sys.availableUpdates.stable.version);
            }
            if (ds.sys.availableUpdates.beta != null) {
                status.update.betaVersion = "v" + getString(ds.sys.availableUpdates.beta.version);
            }
        }

        fillRelayStatus(status, ds);
        updateInputStatus(status, ds, false);
        fillRollerStatus(status, ds.cover0);

        return status;
    }

    @Override
    public void setLedStatus(String ledName, Boolean value) throws ShellyApiException {
    }

    @Override
    public void setSleepTime(int value) throws ShellyApiException {
    }

    @Override
    public ShellyStatusRelay getRelayStatus(int relayIndex) throws ShellyApiException {
        // Update status for a single relay
        getStatus();
        return relayStatus;
    }

    @Override
    public void setRelayTurn(int id, String turnMode) throws ShellyApiException {
        callApi("/rpc/Switch.Set?id=" + id + "&on=" + (SHELLY_API_ON.equals(turnMode) ? "true" : "false"),
                String.class);
    }

    @Override
    public ShellyRollerStatus getRollerStatus(int rollerIndex) throws ShellyApiException {
        if (rollerIndex < rollerStatus.size()) {
            return rollerStatus.get(rollerIndex);
        }
        throw new IllegalArgumentException("Invalid rollerIndex on getRollerStatus");
    }

    @Override
    public void setRollerTurn(int relayIndex, String turnMode) throws ShellyApiException {
        String operation = "";
        switch (turnMode) {
            case SHELLY_ALWD_ROLLER_TURN_OPEN:
                operation = SHELLY2_COVER_CMD_OPEN;
                break;
            case SHELLY_ALWD_ROLLER_TURN_CLOSE:
                operation = SHELLY2_COVER_CMD_CLOSE;
                break;
            case SHELLY_ALWD_ROLLER_TURN_STOP:
                operation = SHELLY2_COVER_CMD_STOP;
                break;
        }

        apiRequest(new Shelly2RpcRequest().withMethod("Cover." + operation).withId(relayIndex));
    }

    @Override
    public void setRollerPos(int relayIndex, int position) throws ShellyApiException {
        apiRequest(
                new Shelly2RpcRequest().withMethod(SHELLYRPC_METHOD_COVER_SETPOS).withId(relayIndex).withPos(position));
    }

    @Override
    public ShellyStatusLight getLightStatus() throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public ShellyShortLightStatus getLightStatus(int index) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setLightParm(int lightIndex, String parm, String value) throws ShellyApiException {
    }

    @Override
    public ShellyShortLightStatus setLightTurn(int id, String turnMode) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setBrightness(int id, int brightness, boolean autoOn) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setLightMode(String mode) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setLightParms(int lightIndex, Map<String, String> parameters) throws ShellyApiException {
    }

    @Override
    public ShellyStatusSensor getSensorStatus() throws ShellyApiException {
        return sensorData;
    }

    @Override
    public void setTimer(int index, String timerName, int value) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setValveMode(boolean auto) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setValvePosition(double value) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setTemperature(int value) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setBoostTime(int value) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void startBoost(int value) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public String resetStaCache() throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setActionURLs() throws ShellyApiException {
    }

    @Override
    public ShellySettingsLogin setCoIoTPeer(String peer) throws ShellyApiException {
        return new ShellySettingsLogin();
    }

    @Override
    public void sendIRKey(String keyCode) throws ShellyApiException, IllegalArgumentException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public void setProfile(int value) throws ShellyApiException {
        throw new ShellyApiException("API call not implemented");
    }

    @Override
    public ShellySettingsLogin getLoginSettings() throws ShellyApiException {
        return new ShellySettingsLogin();
    }

    @Override
    public ShellySettingsLogin setLoginCredentials(String user, String password) throws ShellyApiException {
        return new ShellySettingsLogin();
    }

    @Override
    public String setWiFiRecovery(boolean enable) throws ShellyApiException {
        return "failed";
    }

    @Override
    public String setApRoaming(boolean enable) throws ShellyApiException {
        return "false";
    }

    @Override
    public String setCloud(boolean enabled) throws ShellyApiException {
        return "failed";
    }

    @Override
    public String setDebug(boolean enabled) throws ShellyApiException {
        return "failed";
    }

    @Override
    public String getDebugLog(String id) throws ShellyApiException {
        return "";
    }

    @Override
    public String deviceReboot() throws ShellyApiException {
        // String s = callApi("/rpc/" + SHELLYRPC_METHOD_REBOOT, String.class);
        return apiRequest(SHELLYRPC_METHOD_REBOOT, null, String.class);
    }

    @Override
    public String factoryReset() throws ShellyApiException {
        // String s = callApi("/rpc/" + SHELLYRPC_METHOD_RESET, String.class);
        return apiRequest(SHELLYRPC_METHOD_RESET, null, String.class);
    }

    @Override
    public ShellyOtaCheckResult checkForUpdate() throws ShellyApiException {
        // Shelly2DeviceStatusSysAvlUpdate status = callApi("/rpc/" + SHELLYRPC_METHOD_CHECKUPD,
        // Shelly2DeviceStatusSysAvlUpdate.class);
        Shelly2DeviceStatusSysAvlUpdate status = apiRequest(SHELLYRPC_METHOD_CHECKUPD, null,
                Shelly2DeviceStatusSysAvlUpdate.class);
        ShellyOtaCheckResult result = new ShellyOtaCheckResult();
        result.status = status.stable != null || status.beta != null ? "new" : "ok";
        return result;
    }

    private void asyncApiRequest(String method) throws ShellyApiException {
        Shelly2RpcBaseMessage request = builRequest(config.serviceName, method, null);
        rpcSocket.sendMessage(gson.toJson(request)); // submit, result wull be async
    }

    public <T> T apiRequest(String method, @Nullable Object params, Class<T> classOfT) throws ShellyApiException {
        String json = "";
        Shelly2RpcBaseMessage req = builRequest(config.serviceName, method, params);
        try {
            if (authInfo.realm != null) {
                req.auth = buioldAuthRequest(authInfo, config.userId, config.serviceName, config.password);
            }
            json = httpPost("/rpc", gson.toJson(req));
        } catch (ShellyApiException e) {
            ShellyApiResult res = e.getApiResult();
            String auth = getString(res.authResponse);
            if (res.isHttpAccessUnauthorized() && !auth.isEmpty()) {
                String[] options = auth.split(",");
                for (String o : options) {
                    String key = substringBefore(o, "=").stripLeading().trim();
                    String value = substringAfter(o, "=").replaceAll("\"", "").trim();
                    switch (key) {
                        case "Digest qop":
                            break;
                        case "realm":
                            authInfo.realm = value;
                            break;
                        case "nonce":
                            authInfo.nonce = Long.parseLong(value, 16);
                            break;
                        case "algorithm":
                            authInfo.algorithm = value;
                            break;
                    }
                }
                authInfo.nc = 1;
                req.auth = buioldAuthRequest(authInfo, config.userId, authInfo.realm, config.password);
                json = httpPost("/rpc", gson.toJson(req));
            } else {
                throw e;
            }
        }
        Shelly2RpcBaseMessage rsp = gson.fromJson(json, Shelly2RpcBaseMessage.class);
        json = gson.toJson(rsp.result);
        return fromJson(gson, json, classOfT);
    }

    public <T> T apiRequest(Shelly2RpcRequest request, Class<T> classOfT) throws ShellyApiException {
        return apiRequest(request.method, request.params, classOfT);
    }

    public String apiRequest(Shelly2RpcRequest request) throws ShellyApiException {
        return apiRequest(request.method, request.params, String.class);
    }

    public Shelly2WebSocketInterface getRpcHandler() {
        return this;
    }

    @Override
    public String getCoIoTDescription() {
        return "";
    }
}
