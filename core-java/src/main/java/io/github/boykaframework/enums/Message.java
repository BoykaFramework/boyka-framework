/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package io.github.boykaframework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Different validation messages
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
@AllArgsConstructor
@Getter
public enum Message {
    /**
     * Action is not supported error.
     */
    ACTION_NOT_SUPPORTED_ON_PLATFORM ("({0}) action is not supported on ({1}) platform..."),
    /**
     * Basic auth password is empty.
     */
    AUTH_PASSWORD_REQUIRED ("Basic auth password is required..."),
    /**
     * Base URL is not provided.
     */
    BASE_URL_EMPTY ("Base URL is not provided in the config..."),
    /**
     * Block name in test data file is required.
     */
    BLOCK_NAME_REQUIRED ("Block name is required to read data from file..."),
    /**
     * Browser option not supported Error.
     */
    BROWSER_OPTION_NOT_SUPPORTED ("Browser options not supported for ({0}) browser..."),
    /**
     * Bundle ID is required.
     */
    BUNDLE_ID_REQUIRED ("Bundle ID is required..."),
    /**
     * Remote execution requires capabilities
     */
    CAPABILITIES_REQUIRED_FOR_REMOTE ("Capabilities required for remote execution..."),
    /**
     * Cloud provider does not support this platform.
     */
    CLOUD_NOT_SUPPORTED ("Cloud provider does not support ({0}) platform..."),
    /**
     * Config key is not found.
     */
    CONFIG_KEY_NOT_FOUND (
        "The key ({0}) mentioned is not found in the config file, please provide a valid keys from the following ({1})..."),
    /**
     * Content type must be set before setting request body
     */
    CONTENT_TYPE_NOT_SET ("Content type must be set before setting request body..."),
    /**
     * Context switching not allowed for apps other than Hybrid app.
     */
    CONTEXT_SWITCHING_NOT_ALLOWED (
        "Context switching is not allowed for application other than Hybrid, current application type is ({0})..."),
    /**
     * Delay should be less than 1000ms.
     */
    DELAY_OVERFLOW ("Delay should not be < 0, or > 1000ms may slow your run further..."),
    /**
     * Error when driver is null.
     */
    DRIVER_CANNOT_BE_NULL ("Driver cannot be null, make sure you started the session correctly.."),
    /**
     * WebDriver's error occurred.
     */
    DRIVER_ERROR_OCCURRED ("Error encountered by Driver: {0}"),
    /**
     * Element cannot be null.
     */
    ELEMENT_CANNOT_BE_NULL ("Element cannot be null, set an element to perform action on it..."),
    /**
     * Element not found.
     */
    ELEMENT_NOT_FOUND ("Element ({0}) not found for platform ({1})..."),
    /**
     * Empty browser is not allowed.
     */
    EMPTY_BROWSER_NOT_ALLOWED ("Browser type cannot be empty in the config..."),
    /**
     * Error while calling setter method.
     */
    ERROR_CALLING_SETTER ("Error encountered when calling setter method [{0}] in [{1}] data class..."),
    /**
     * Error while creating log file for a log type.
     */
    ERROR_CREATING_LOGS ("Error occurred while creating log file for log type: {}..."),
    /**
     * Error deleting log files.
     */
    ERROR_DELETING_LOGS ("Error deleting log files..."),
    /**
     * Deselect from dropdown Error.
     */
    ERROR_DESELECT_FROM_DROPDOWN ("Cannot deselect from dropdown, it should be Multi-select box..."),
    /**
     * Error executing request
     */
    ERROR_EXECUTING_REQUEST ("Error occurred while executing request..."),
    /**
     * Error while getting classes.
     */
    ERROR_GETTING_CLASSES ("Error occurred while getting all classes..."),
    /**
     * No constructor in the test class.
     */
    ERROR_NO_CTOR ("Constructor not found in Test data class [{0}]..."),
    /**
     * Error occurred while parsing request body.
     */
    ERROR_PARSING_REQUEST_BODY ("Error occurred while parsing request body..."),
    /**
     * Parsing Error while parsing the response body.
     */
    ERROR_PARSING_RESPONSE_BODY ("Error occurred while parsing response body..."),
    /**
     * Error occurred while quitting driver.
     */
    ERROR_QUITTING_DRIVER ("Error quitting driver..."),
    /**
     * Error reading data file.
     */
    ERROR_READING_DATA_FILE ("Error encountered while reading data from file [{0}]..."),
    /**
     * Error reading file
     */
    ERROR_READING_FILE ("Error occurred reading file ({0})..."),
    /**
     * Error while saving screenshot.
     */
    ERROR_SAVING_SCREENSHOT ("Error saving screenshot"),
    /**
     * Error saving video recording.
     */
    ERROR_SAVING_VIDEO ("Error occurred while saving video recording..."),
    /**
     * Error due to server not running.
     */
    ERROR_SERVER_NOT_RUNNING ("Error occurred, server not running: {0}..."),
    /**
     * Setter method not found.
     */
    ERROR_SETTER_NOT_FOUND ("Setter method [{0}] not found in [{1}] class of type [{2}]..."),
    /**
     * Error starting Appium server.
     */
    ERROR_STARTING_SERVER ("Error occurred starting Appium server: {0}..."),
    /**
     * Error stopping Appium server.
     */
    ERROR_STOPPING_SERVER ("Error occurred stopping Appium server: {0}..."),
    /**
     * Error while sleeping.
     */
    ERROR_WHILE_SLEEPING ("Error encountered while pausing the thread..."),
    /**
     * Error writing file
     */
    ERROR_WRITING_FILE ("Error occurred writing file ({0})..."),
    /**
     * Error while writing logs to file.
     */
    ERROR_WRITING_LOGS ("Error occurred while writing logs..."),
    /**
     * Finger should be within the bounds.
     */
    FINGER_OUT_OF_BOUND ("Finger co-ordinates ({0}) is outside the bounds ({1})..."),
    /**
     * Host name is required for Remote execution
     */
    HOSTNAME_REQUIRED_FOR_REMOTE ("Host name is required for remote driver execution..."),
    /**
     * Index out of bounds.
     */
    INDEX_OUT_OF_BOUNDS ("Index [{0}] is out of bounds..."),
    /**
     * Invalid browser selected
     */
    INVALID_BROWSER ("NONE Browser type is not allowed for Web platform..."),
    /**
     * No such key found
     */
    INVALID_HEADER_KEY ("No such key {0} found..."),
    /**
     * Invalid listener class provided.
     */
    INVALID_LISTENER_FOUND ("Invalid Listener class ({0}) provided in the config..."),
    /**
     * Invalid platform for operation
     */
    INVALID_PLATFORM_FOR_OPERATION ("Platform ({0}) is not supported for this setting..."),
    /**
     * Invalid Remote session URL.
     */
    INVALID_REMOTE_URL ("Invalid remote url generated..."),
    /**
     * Swipe distance Error.
     */
    INVALID_SWIPE_DISTANCE ("Swipe Distance should be greater than 0% and less than 100%..."),
    /**
     * Malformed JSON syntax error.
     */
    JSON_SYNTAX_ERROR ("JSON syntax error..."),
    /**
     * No API setting found
     */
    NO_API_SETTINGS_FOUND ("No settings found for API for key {0}..."),
    /**
     * Empty response body to parse as JsonPath.
     */
    NO_BODY_TO_PARSE ("No body to parse as JsonPath..."),
    /**
     * No JSON file found
     */
    NO_JSON_FILE_FOUND ("JSON file ({0}) not found..."),
    /**
     * No virtual keyboard for Web platform.
     */
    NO_KEYBOARD_ERROR ("There is no virtual keyboard for Web..."),
    /**
     * Null remote url.
     */
    NULL_REMOTE_URL ("Remote URL is null..."),
    /**
     * Missing Page load strategy/
     */
    PAGE_LOAD_STRATEGY_MISSING ("Page load strategy is missing in the config..."),
    /**
     * Password required for cloud execution
     */
    PASSWORD_REQUIRED_FOR_CLOUD ("Password is required for cloud execution..."),
    /**
     * Path should be a folder / directory.
     */
    PATH_NOT_DIRECTORY ("Path [{0}] is not a folder. Only folder path should be provided..."),
    /**
     * Protocol is required for host name
     */
    PROTOCOL_REQUIRED_FOR_HOST ("Protocol is required for host ({0})..."),
    /**
     * Schema validation assert failure
     */
    RESPONSE_SCHEMA_NOT_MATCHING ("Schema validation assert failure..."),
    /**
     * Session already cleared.
     */
    SESSION_ALREADY_CLEARED ("Session already cleared..."),
    /**
     * Session already created.
     */
    SESSION_ALREADY_CREATED ("Session is already created for ({0}) persona..."),
    /**
     * Error when starting session.
     */
    SESSION_NOT_STARTED ("Session could not be started..."),
    /**
     * Session persona cannot be null.
     */
    SESSION_PERSONA_CANNOT_BE_NULL ("Session Persona cannot be empty or null..."),
    /**
     * SSL Context Error.
     */
    SSL_ERROR ("Error encountered in SSL Context: {0}..."),
    /**
     * Test Error.
     */
    TEST_ERROR ("Test error..."),
    /**
     * Unsupported Excel value format.
     */
    UNSUPPORTED_EXCEL_VALUE_FORMAT (
        "The Excel value format of ({0}) is not supported. Please create a GitHub issue " + "with details to handle the same."),
    /**
     * User name required for cloud execution
     */
    USER_NAME_REQUIRED_FOR_CLOUD ("User name is required for cloud execution...");

    private final String messageText;
}
