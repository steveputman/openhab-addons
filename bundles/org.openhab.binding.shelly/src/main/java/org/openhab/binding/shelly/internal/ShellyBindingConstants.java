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
package org.openhab.binding.shelly.internal;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link ShellyBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Markus Michels - Initial contribution
 */
@NonNullByDefault
public class ShellyBindingConstants {

    public static final String VENDOR = "Shelly";
    public static final String BINDING_ID = "shelly";
    public static final String SYSTEM_ID = "system";

    // Type names
    public static final String THING_TYPE_SHELLY1_STR = "shelly1";
    public static final String THING_TYPE_SHELLY1L_STR = "shelly1l";
    public static final String THING_TYPE_SHELLY1PM_STR = "shelly1pm";
    public static final String THING_TYPE_SHELLYEM_STR = "shellyem";
    public static final String THING_TYPE_SHELLY3EM_STR = "shellyem3"; // bad: misspelled product name, it's 3EM
    public static final String THING_TYPE_SHELLY2_PREFIX = "shellyswitch";
    public static final String THING_TYPE_SHELLY2_RELAY_STR = "shelly2-relay";
    public static final String THING_TYPE_SHELLY2_ROLLER_STR = "shelly2-roller";
    public static final String THING_TYPE_SHELLY25_PREFIX = "shellyswitch25";
    public static final String THING_TYPE_SHELLY25_RELAY_STR = "shelly25-relay";
    public static final String THING_TYPE_SHELLY25_ROLLER_STR = "shelly25-roller";
    public static final String THING_TYPE_SHELLY4PRO_STR = "shelly4pro";
    public static final String THING_TYPE_SHELLYPLUG_STR = "shellyplug";
    public static final String THING_TYPE_SHELLYPLUGS_STR = "shellyplugs";
    public static final String THING_TYPE_SHELLYPLUGU1_STR = "shellyplugu1"; // Shely Plug US
    public static final String THING_TYPE_SHELLYDIMMER_STR = "shellydimmer";
    public static final String THING_TYPE_SHELLYDIMMER2_STR = "shellydimmer2";
    public static final String THING_TYPE_SHELLYIX3_STR = "shellyix3";
    public static final String THING_TYPE_SHELLYBULB_STR = "shellybulb";
    public static final String THING_TYPE_SHELLYDUO_STR = "shellybulbduo";
    public static final String THING_TYPE_SHELLYVINTAGE_STR = "shellyvintage";
    public static final String THING_TYPE_SHELLYRGBW2_PREFIX = "shellyrgbw2";
    public static final String THING_TYPE_SHELLYRGBW2_COLOR_STR = THING_TYPE_SHELLYRGBW2_PREFIX + "-color";
    public static final String THING_TYPE_SHELLYRGBW2_WHITE_STR = THING_TYPE_SHELLYRGBW2_PREFIX + "-white";
    public static final String THING_TYPE_SHELLYDUORGBW_STR = "shellycolorbulb";
    public static final String THING_TYPE_SHELLYHT_STR = "shellyht";
    public static final String THING_TYPE_SHELLYSMOKE_STR = "shellysmoke";
    public static final String THING_TYPE_SHELLYGAS_STR = "shellygas";
    public static final String THING_TYPE_SHELLYFLOOD_STR = "shellyflood";
    public static final String THING_TYPE_SHELLYDOORWIN_STR = "shellydw";
    public static final String THING_TYPE_SHELLYDOORWIN2_STR = "shellydw2";
    public static final String THING_TYPE_SHELLYEYE_STR = "shellyseye";
    public static final String THING_TYPE_SHELLYSENSE_STR = "shellysense";
    public static final String THING_TYPE_SHELLYTRV_STR = "shellytrv";
    public static final String THING_TYPE_SHELLYMOTION_STR = "shellymotion";
    public static final String THING_TYPE_SHELLYBUTTON1_STR = "shellybutton1";
    public static final String THING_TYPE_SHELLYBUTTON2_STR = "shellybutton2";
    public static final String THING_TYPE_SHELLYUNI_STR = "shellyuni";

    // Shelly Plus/Pro
    public static final String THING_TYPE_SHELLYPLUS1_STR = "shellyplus1";
    public static final String THING_TYPE_SHELLYPLUS1PM_STR = "shellyplus1pm";
    public static final String THING_TYPE_SHELLYPLUS2PM_RELAY_STR = "shellyplus2pm-relay";
    public static final String THING_TYPE_SHELLYPLUS2PM_ROLLER_STR = "shellyplus2pm-roller";
    public static final String THING_TYPE_SHELLYPLUSI4_STR = "shellyplusi4";
    public static final String THING_TYPE_SHELLYPLUSHT_STR = "shellyplusht";
    public static final String THING_TYPE_SHELLYPRO1_STR = "shellypro1";
    public static final String THING_TYPE_SHELLYPRO1PM_STR = "shellypro1pm";
    public static final String THING_TYPE_SHELLYPRO2_RELAY_STR = "shellypro2-relay";
    public static final String THING_TYPE_SHELLYPRO2_ROLLER_STR = "shellypro2-roller";
    public static final String THING_TYPE_SHELLYPRO2PM_RELAY_STR = "shellypro2pm-relay";
    public static final String THING_TYPE_SHELLYPRO2PM_ROLLER_STR = "shellypro2pm-roller";
    public static final String THING_TYPE_SHELLYPRO4PM_STR = "shellypro4pm";

    public static final String THING_TYPE_SHELLYPROTECTED_STR = "shellydevice";
    public static final String THING_TYPE_SHELLYUNKNOWN_STR = "shellyunknown";

    // Device Types
    public static final String SHELLYDT_1 = "SHSW-1";
    public static final String SHELLYDT_1PM = "SHSW-PM";
    public static final String SHELLYDT_1L = "SHSW-L";
    public static final String SHELLYDT_SHPLG = "SHPLG-1";
    public static final String SHELLYDT_SHPLG_S = "SHPLG-S";
    public static final String SHELLYDT_SHPLG_U1 = "SHPLG-U1";
    public static final String SHELLYDT_SHELLY2 = "SHSW-21";
    public static final String SHELLYDT_SHELLY25 = "SHSW-25";
    public static final String SHELLYDT_SHPRO = "SHSW-44";
    public static final String SHELLYDT_EM = "SHEM";
    public static final String SHELLYDT_3EM = "SHEM-3";
    public static final String SHELLYDT_HT = "SHHT-1";
    public static final String SHELLYDT_DW = "SHDW-1";
    public static final String SHELLYDT_DW2 = "SHDW-2";
    public static final String SHELLYDT_SENSE = "SHSEN-1";
    public static final String SHELLYDT_MOTION = "SHMOS-01";
    public static final String SHELLYDT_MOTION2 = "SHMOS-02";
    public static final String SHELLYDT_GAS = "SHGS-1";
    public static final String SHELLYDT_DIMMER = "SHDM-1";
    public static final String SHELLYDT_DIMMER2 = "SHDM-2";
    public static final String SHELLYDT_IX3 = "SHIX3-1";
    public static final String SHELLYDT_BULB = "SHBLB-1";
    public static final String SHELLYDT_DUO = "SHBDUO-1";
    public static final String SHELLYDT_DUORGBW = "SHCB-1";
    public static final String SHELLYDT_VINTAGE = "SHVIN-1";
    public static final String SHELLYDT_RGBW2 = "SHRGBW2";
    public static final String SHELLYDT_BUTTON1 = "SHBTN-1";
    public static final String SHELLYDT_BUTTON2 = "SHBTN-2";
    public static final String SHELLYDT_UNI = "SHUNI-1";
    public static final String SHELLYDT_TRV = "SHTRV-01";

    // Shelly Plus / Pro Series
    public static final String SHELLYDT_PLUS1 = "SNSW-001X16EU";
    public static final String SHELLYDT_PLUS1PM = "SNSW-001P16EU";
    public static final String SHELLYDT_PLUS2PM_RELAY = "SNSW-002P16EU-relay";
    public static final String SHELLYDT_PLUS2PM_ROLLER = "SNSW-002P16EU-roller";
    public static final String SHELLYDT_PRO1 = "SPSW-001XE16EU";
    public static final String SHELLYDT_PRO1PM = "SPSW-001PE16EU";
    public static final String SHELLYDT_PRO2_RELAY = "SPSW-002XE16EU-relay";
    public static final String SHELLYDT_PRO2_ROLLER = "SPSW-002XE16EU-roller";
    public static final String SHELLYDT_PRO2PM_RELAY = "SPSW-002PE16EU-relay";
    public static final String SHELLYDT_PRO2PM_ROLLER = "SPSW-002PE16EU-roller";
    public static final String SHELLYDT_PRO4PM = "SPSW-004PE16EU";
    public static final String SHELLYDT_PLUSI4 = "SNSN-0024X";
    public static final String SHELLYDT_PLUSHT = "SNSN-0013A";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_SHELLY1 = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLY1_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY1L = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLY1L_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY1PM = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLY1PM_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYEM = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYEM_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY3EM = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLY3EM_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY2_RELAY = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLY2_RELAY_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY2_ROLLER = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLY2_ROLLER_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY25_RELAY = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLY25_RELAY_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY25_ROLLER = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLY25_ROLLER_STR);
    public static final ThingTypeUID THING_TYPE_SHELLY4PRO = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLY4PRO_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUG = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYPLUG_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUGS = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYPLUGS_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUGU1 = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPLUGU1_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYUNI = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYUNI_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYDIMMER = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYDIMMER_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYDIMMER2 = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYDIMMER2_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYIX3 = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYIX3_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYBULB = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYBULB_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYDUO = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYDUO_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYVINTAGE = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYVINTAGE_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYDUORGBW = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYDUORGBW_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYHT = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYHT_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYSENSE = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYSENSE_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYSMOKE = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYSMOKE_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYGAS = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYGAS_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYFLOOD = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYFLOOD_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYDOORWIN = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYDOORWIN_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYDOORWIN2 = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYDOORWIN2_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYTRV = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYTRV_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYBUTTON1 = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYBUTTON1_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYBUTTON2 = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYBUTTON2_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYEYE = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYEYE_STR);
    public static final ThingTypeUID THING_TYPE_SHELLMOTION = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYMOTION_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYRGBW2_COLOR = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYRGBW2_COLOR_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYRGBW2_WHITE = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYRGBW2_WHITE_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPROTECTED = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPROTECTED_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYUNKNOWN = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYUNKNOWN_STR);

    // Shelly Plus/Pro
    public static final ThingTypeUID THING_TYPE_SHELLYPLUS1 = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYPLUS1_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUS1PM = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPLUS1PM_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUS2PM_RELAY = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPLUS2PM_RELAY_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUS2PM_ROLLER = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPLUS2PM_ROLLER_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO1 = new ThingTypeUID(BINDING_ID, THING_TYPE_SHELLYPRO1_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO1PM = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPRO1PM_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO2_RELAY = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPRO2_RELAY_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO2_ROLLER = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPRO2_ROLLER_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO2PM_RELAY = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPRO2PM_RELAY_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO2PM_ROLLER = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPRO2PM_ROLLER_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPRO4PM = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPRO4PM_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUSI4 = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPLUSI4_STR);
    public static final ThingTypeUID THING_TYPE_SHELLYPLUSHT = new ThingTypeUID(BINDING_ID,
            THING_TYPE_SHELLYPLUSHT_STR);

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.unmodifiableSet(Stream.of(
            THING_TYPE_SHELLY1, THING_TYPE_SHELLY1L, THING_TYPE_SHELLY1PM, THING_TYPE_SHELLYEM, THING_TYPE_SHELLY3EM,
            THING_TYPE_SHELLY2_RELAY, THING_TYPE_SHELLY2_ROLLER, THING_TYPE_SHELLY25_RELAY, THING_TYPE_SHELLY25_ROLLER,
            THING_TYPE_SHELLY4PRO, THING_TYPE_SHELLYPLUG, THING_TYPE_SHELLYPLUGS, THING_TYPE_SHELLYPLUGU1,
            THING_TYPE_SHELLYUNI, THING_TYPE_SHELLYDIMMER, THING_TYPE_SHELLYDIMMER2, THING_TYPE_SHELLYIX3,
            THING_TYPE_SHELLYBULB, THING_TYPE_SHELLYDUO, THING_TYPE_SHELLYVINTAGE, THING_TYPE_SHELLYDUORGBW,
            THING_TYPE_SHELLYRGBW2_COLOR, THING_TYPE_SHELLYRGBW2_WHITE, THING_TYPE_SHELLYHT, THING_TYPE_SHELLYTRV,
            THING_TYPE_SHELLYSENSE, THING_TYPE_SHELLYEYE, THING_TYPE_SHELLYSMOKE, THING_TYPE_SHELLYGAS,
            THING_TYPE_SHELLYFLOOD, THING_TYPE_SHELLYDOORWIN, THING_TYPE_SHELLYDOORWIN2, THING_TYPE_SHELLYBUTTON1,
            THING_TYPE_SHELLYBUTTON2, THING_TYPE_SHELLMOTION, THING_TYPE_SHELLMOTION, THING_TYPE_SHELLYPLUS1,
            THING_TYPE_SHELLYPLUS1PM, THING_TYPE_SHELLYPLUS2PM_RELAY, THING_TYPE_SHELLYPLUS2PM_ROLLER,
            THING_TYPE_SHELLYPRO1, THING_TYPE_SHELLYPRO1PM, THING_TYPE_SHELLYPRO2_RELAY, THING_TYPE_SHELLYPRO2_ROLLER,
            THING_TYPE_SHELLYPRO2PM_RELAY, THING_TYPE_SHELLYPRO2PM_ROLLER, THING_TYPE_SHELLYPRO4PM,
            THING_TYPE_SHELLYPLUSI4, THING_TYPE_SHELLYPLUSHT, THING_TYPE_SHELLYPROTECTED, THING_TYPE_SHELLYUNKNOWN)
            .collect(Collectors.toSet()));

    // Thing Configuration Properties
    public static final String CONFIG_DEVICEIP = "deviceIp";
    public static final String CONFIG_HTTP_USERID = "userId";
    public static final String CONFIG_HTTP_PASSWORD = "password";
    public static final String CONFIG_UPDATE_INTERVAL = "updateInterval";

    public static final String PROPERTY_SERVICE_NAME = "serviceName";
    public static final String PROPERTY_DEV_NAME = "deviceName";
    public static final String PROPERTY_DEV_TYPE = "deviceType";
    public static final String PROPERTY_DEV_GEN = "deviceGeneration";
    public static final String PROPERTY_DEV_MODE = "deviceMode";
    public static final String PROPERTY_HWREV = "deviceHwRev";
    public static final String PROPERTY_HWBATCH = "deviceHwBatch";
    public static final String PROPERTY_UPDATE_PERIOD = "devUpdatePeriod";
    public static final String PROPERTY_NUM_RELAYS = "numberRelays";
    public static final String PROPERTY_NUM_ROLLERS = "numberRollers";
    public static final String PROPERTY_NUM_METER = "numberMeters";
    public static final String PROPERTY_LAST_ACTIVE = "lastActive";
    public static final String PROPERTY_WIFI_NETW = "wifiNetwork";
    public static final String PROPERTY_UPDATE_STATUS = "updateStatus";
    public static final String PROPERTY_UPDATE_AVAILABLE = "updateAvailable";
    public static final String PROPERTY_UPDATE_CURR_VERS = "updateCurrentVersion";
    public static final String PROPERTY_UPDATE_NEW_VERS = "updateNewVersion";
    public static final String PROPERTY_COAP_DESCR = "coapDeviceDescr";
    public static final String PROPERTY_COAP_VERSION = "coapVersion";
    public static final String PROPERTY_COIOTAUTO = "coiotAutoEnable";

    // Relay
    public static final String CHANNEL_GROUP_RELAY_CONTROL = "relay";
    public static final String CHANNEL_OUTPUT_NAME = "outputName";
    public static final String CHANNEL_OUTPUT = "output";
    public static final String CHANNEL_INPUT = "input";
    public static final String CHANNEL_INPUT1 = "input1";
    public static final String CHANNEL_INPUT2 = "input2";
    public static final String CHANNEL_BRIGHTNESS = "brightness";

    public static final String CHANNEL_TIMER_AUTOON = "autoOn";
    public static final String CHANNEL_TIMER_AUTOOFF = "autoOff";
    public static final String CHANNEL_TIMER_ACTIVE = "timerActive";

    // Roller
    public static final String CHANNEL_GROUP_ROL_CONTROL = "roller";
    public static final String CHANNEL_ROL_CONTROL_CONTROL = "control";
    public static final String CHANNEL_ROL_CONTROL_POS = "rollerpos";
    public static final String CHANNEL_ROL_CONTROL_FAV = "rollerFav";
    public static final String CHANNEL_ROL_CONTROL_TIMER = "timer";
    public static final String CHANNEL_ROL_CONTROL_STATE = "state";
    public static final String CHANNEL_ROL_CONTROL_STOPR = "stopReason";
    public static final String CHANNEL_ROL_CONTROL_SAFETY = "safety";

    // Dimmer
    public static final String CHANNEL_GROUP_DIMMER_CONTROL = CHANNEL_GROUP_RELAY_CONTROL;

    // Power meter
    public static final String CHANNEL_GROUP_METER = "meter";
    public static final String CHANNEL_METER_CURRENTWATTS = "currentWatts";
    public static final String CHANNEL_METER_LASTMIN = "lastPower";
    public static final String CHANNEL_METER_LASTMIN1 = CHANNEL_METER_LASTMIN + "1";
    public static final String CHANNEL_METER_TOTALKWH = "totalKWH";
    public static final String CHANNEL_EMETER_TOTALRET = "returnedKWH";
    public static final String CHANNEL_EMETER_REACTWATTS = "reactiveWatts";
    public static final String CHANNEL_EMETER_VOLTAGE = "voltage";
    public static final String CHANNEL_EMETER_CURRENT = "current";
    public static final String CHANNEL_EMETER_PFACTOR = "powerFactor";

    public static final String CHANNEL_GROUP_SENSOR = "sensors";
    public static final String CHANNEL_SENSOR_TEMP = "temperature";
    public static final String CHANNEL_SENSOR_HUM = "humidity";
    public static final String CHANNEL_SENSOR_LUX = "lux";
    public static final String CHANNEL_SENSOR_PPM = "ppm";
    public static final String CHANNEL_SENSOR_VOLTAGE = "voltage";
    public static final String CHANNEL_SENSOR_ILLUM = "illumination";
    public static final String CHANNEL_SENSOR_VIBRATION = "vibration";
    public static final String CHANNEL_SENSOR_TILT = "tilt";
    public static final String CHANNEL_SENSOR_FLOOD = "flood";
    public static final String CHANNEL_SENSOR_SMOKE = "smoke";
    public static final String CHANNEL_SENSOR_STATE = "state";
    public static final String CHANNEL_SENSOR_VALVE = "valve";
    public static final String CHANNEL_SENSOR_SSTATE = "status"; // Shelly Gas
    public static final String CHANNEL_SENSOR_MOTION_ACT = "motionActive";
    public static final String CHANNEL_SENSOR_MOTION = "motion";
    public static final String CHANNEL_SENSOR_MOTION_TS = "motionTimestamp";
    public static final String CHANNEL_SENSOR_SLEEPTIME = "sensorSleepTime";
    public static final String CHANNEL_SENSOR_ALARM_STATE = "alarmState";
    public static final String CHANNEL_SENSOR_ERROR = "lastError";

    public static final String CHANNEL_CONTROL_SETTEMP = "targetTemp"; // Shelly TRV: target temp
    public static final String CHANNEL_CONTROL_POSITION = "position"; // Shelly TRV: Valve position
    public static final String CHANNEL_CONTROL_MODE = "mode"; // Shelly TRV
    public static final String CHANNEL_CONTROL_PROFILE = "profile"; // Shelly TRV
    public static final String CHANNEL_CONTROL_BCONTROL = "boost"; // Shelly TRV
    public static final String CHANNEL_CONTROL_BTIMER = "boostTimer"; // Shelly TRV

    // External sensors for Shelly1/1PM
    public static final String CHANNEL_ESENDOR_TEMP1 = CHANNEL_SENSOR_TEMP + "1";
    public static final String CHANNEL_ESENDOR_TEMP2 = CHANNEL_SENSOR_TEMP + "2";
    public static final String CHANNEL_ESENDOR_TEMP3 = CHANNEL_SENSOR_TEMP + "3";
    public static final String CHANNEL_ESENDOR_HUMIDITY = CHANNEL_SENSOR_HUM;

    public static final String CHANNEL_GROUP_CONTROL = "control";
    public static final String CHANNEL_SENSE_KEY = "key";

    public static final String CHANNEL_GROUP_BATTERY = "battery";
    public static final String CHANNEL_SENSOR_BAT_LEVEL = "batteryLevel";
    public static final String CHANNEL_SENSOR_BAT_LOW = "lowBattery";

    public static final String CHANNEL_GROUP_LIGHT_CONTROL = "control";
    public static final String CHANNEL_LIGHT_COLOR_MODE = "mode";
    public static final String CHANNEL_LIGHT_POWER = "power";
    public static final String CHANNEL_LIGHT_DEFSTATE = "defaultState";
    public static final String CHANNEL_GROUP_LIGHT_CHANNEL = "channel";

    // Bulb/RGBW2 in color mode
    public static final String CHANNEL_GROUP_COLOR_CONTROL = "color";
    public static final String CHANNEL_COLOR_PICKER = "hsb";
    public static final String CHANNEL_COLOR_FULL = "full";
    public static final String CHANNEL_COLOR_RED = "red";
    public static final String CHANNEL_COLOR_GREEN = "green";
    public static final String CHANNEL_COLOR_BLUE = "blue";
    public static final String CHANNEL_COLOR_WHITE = "white";
    public static final String CHANNEL_COLOR_GAIN = "gain";
    public static final String CHANNEL_COLOR_EFFECT = "effect";

    // Bulb/RGBW2/Dup in White Mode
    public static final String CHANNEL_GROUP_WHITE_CONTROL = "white";
    public static final String CHANNEL_COLOR_TEMP = "temperature";

    // Device Status
    public static final String CHANNEL_GROUP_DEV_STATUS = "device";
    public static final String CHANNEL_DEVST_NAME = "deviceName";
    public static final String CHANNEL_DEVST_UPTIME = "uptime";
    public static final String CHANNEL_DEVST_HEARTBEAT = "heartBeat";
    public static final String CHANNEL_DEVST_RSSI = "wifiSignal";
    public static final String CHANNEL_DEVST_ITEMP = "internalTemp";
    public static final String CHANNEL_DEVST_WAKEUP = "wakeupReason";
    public static final String CHANNEL_DEVST_ALARM = "alarm";
    public static final String CHANNEL_DEVST_ACCUWATTS = "accumulatedWatts";
    public static final String CHANNEL_DEVST_ACCUTOTAL = "accumulatedWTotal";
    public static final String CHANNEL_DEVST_ACCURETURNED = "accumulatedReturned";
    public static final String CHANNEL_DEVST_CHARGER = "charger";
    public static final String CHANNEL_DEVST_UPDATE = "updateAvailable";
    public static final String CHANNEL_DEVST_SELFTTEST = "selfTest";
    public static final String CHANNEL_DEVST_VOLTAGE = "supplyVoltage";
    public static final String CHANNEL_DEVST_CALIBRATED = "calibrated";
    public static final String CHANNEL_DEVST_SCHEDULE = "schedule";

    public static final String CHANNEL_LED_STATUS_DISABLE = "statusLed";
    public static final String CHANNEL_LED_POWER_DISABLE = "powerLed";
    // Button/xi3
    public static final String CHANNEL_GROUP_STATUS = "status";
    public static final String CHANNEL_STATUS_EVENTTYPE = "lastEvent";
    public static final String CHANNEL_STATUS_EVENTTYPE1 = CHANNEL_STATUS_EVENTTYPE + "1";
    public static final String CHANNEL_STATUS_EVENTTYPE2 = CHANNEL_STATUS_EVENTTYPE + "2";
    public static final String CHANNEL_STATUS_EVENTCOUNT = "eventCount";
    public static final String CHANNEL_STATUS_EVENTCOUNT1 = CHANNEL_STATUS_EVENTCOUNT + "1";
    public static final String CHANNEL_STATUS_EVENTCOUNT2 = CHANNEL_STATUS_EVENTCOUNT + "2";

    // General
    public static final String CHANNEL_LAST_UPDATE = "lastUpdate";
    public static final String CHANNEL_EVENT_TRIGGER = "event";
    public static final String CHANNEL_BUTTON_TRIGGER = "button";
    public static final String CHANNEL_BUTTON_TRIGGER1 = CHANNEL_BUTTON_TRIGGER + "1";
    public static final String CHANNEL_BUTTON_TRIGGER2 = CHANNEL_BUTTON_TRIGGER + "2";

    public static final String SERVICE_TYPE = "_http._tcp.local.";
    public static final String SHELLY_API_MIN_FWVERSION = "v1.8";// v1.5.7+
    public static final String SHELLY_API_MIN_FWCOIOT = "v1.6";// v1.6.0+
    public static final String SHELLY_API_FWCOIOT2 = "v1.8";// CoAP 2 with FW 1.8+
    public static final String SHELLY_API_FW_110 = "v1.10"; // FW 1.10 or newer detected, activates some add feature

    // Alarm types/messages
    public static final String ALARM_TYPE_NONE = "NONE";
    public static final String ALARM_TYPE_RESTARTED = "RESTARTED";
    public static final String ALARM_TYPE_OVERTEMP = "OVERTEMP";
    public static final String ALARM_TYPE_OVERPOWER = "OVERPOWER";
    public static final String ALARM_TYPE_OVERLOAD = "OVERLOAD";
    public static final String ALARM_TYPE_LOADERR = "LOAD_ERROR";
    public static final String ALARM_TYPE_SENSOR_ERROR = "SENSOR_ERROR";
    public static final String ALARM_TYPE_LOW_BATTERY = "LOW_BATTERY";
    public static final String ALARM_TYPE_VALVE_ERROR = "VALVE_ERROR";
    public static final String EVENT_TYPE_VIBRATION = "VIBRATION";

    // Event types
    public static final String EVENT_TYPE_RELAY = "relay";
    public static final String EVENT_TYPE_ROLLER = "roller";
    public static final String EVENT_TYPE_LIGHT = "light";
    public static final String EVENT_TYPE_SENSORDATA = "report";

    // URI for the EventServlet
    public static final String SHELLY_CALLBACK_URI = "/shelly/event";

    public static final int DIM_STEPSIZE = 5;

    // Formatting: Number of scaling digits
    public static final int DIGITS_NONE = 0;
    public static final int DIGITS_WATT = 2;
    public static final int DIGITS_KWH = 3;
    public static final int DIGITS_VOLT = 1;
    public static final int DIGITS_TEMP = 1;
    public static final int DIGITS_LUX = 0;
    public static final int DIGITS_PERCENT = 1;

    public static final int SHELLY_API_TIMEOUT_MS = 15000;
    public static final int UPDATE_STATUS_INTERVAL_SECONDS = 3; // check for updates every x sec
    public static final int UPDATE_SKIP_COUNT = 20; // update every x triggers or when a key was pressed
    public static final int UPDATE_MIN_DELAY = 15;// update every x triggers or when a key was pressed
    public static final int UPDATE_SETTINGS_INTERVAL_SECONDS = 60; // check for updates every x sec
    public static final int HEALTH_CHECK_INTERVAL_SEC = 300; // Health check interval, 5min
    public static final int VIBRATION_FILTER_SEC = 5; // Absore duplicate vibration events for xx sec
}
