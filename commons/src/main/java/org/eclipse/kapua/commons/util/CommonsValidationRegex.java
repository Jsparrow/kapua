/*******************************************************************************
 * Copyright (c) 2018 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.commons.util;

import java.util.regex.Pattern;

public enum CommonsValidationRegex implements ValidationRegex {

    /**
     * ^[a-zA-Z0-9\-]{3,}$
     */
    SIMPLE_NAME_REGEXP("^[a-zA-Z0-9\\-]{3,}$"),

    /**
     * ^[a-zA-Z0-9\_\-]{3,}$
     */
    NAME_REGEXP("^[a-zA-Z0-9\\_\\-]{3,}$"),

    /**
     * ^[a-zA-Z0-9\ \_\-]{3,}$
     */
    NAME_SPACE_REGEXP("^[a-zA-Z0-9\\ \\_\\-]{3,}$"),

    /**
     * ^.*(?=.{12,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&amp;+=!\~\|]).*$
     */
    PASSWORD_REGEXP("^.*(?=.{12,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!\\\"\\#$%&'()*+,\\-./:;<=>?@\\[\\]\\\\^_`{|}~]).*$"),

    /**
     * ^(\w+)([-+.][\w]+)*@(\w[-\w]*\.){1,5}([A-Za-z]){2,4}$
     */
    EMAIL_REGEXP("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$"),

    /**
     * (^(http://)|(https://)|())([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])($)
     */
    IP_ADDRESS_REGEXP("(^(http://)|(https://)|())([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])($)"),

    /**
     * The standard (IEEE 802) format for printing MAC-48 addresses in human-friendly
     * <p>
     * ^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$
     */
    MAC_ADDRESS_REGEXP("^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$"),

    /**
     * (^(http://)|(https://))(((127\\.0\\.0\\.1))|
     * ((10\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])))|
     * ((172\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])))|
     * ((192\\.168\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])))$)
     */
    LOCAL_IP_ADDRESS_REGEXP(new StringBuilder().append("(^(http://)|(https://))(((127\\.0\\.0\\.1))|").append("((10\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])))|").append("((172\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])))|").append("((192\\.168\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])))$)").toString()),

    /**
     * ^[a-zA-Z][a-zA-Z0-9\-\.\+]{0,}$
     */
    URI_SCHEME("^[a-zA-Z][a-zA-Z0-9\\-\\.\\+]{0,}$"),

    /**
     * ^((?![0-9\.]+$)(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\-]*[a-zA-Z0-9])\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\-]*[A-Za-z0-9])|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))$
     */
    URI_DNS("^((?![0-9\\.]+$)(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))$");

    private Pattern pattern;

    CommonsValidationRegex(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
