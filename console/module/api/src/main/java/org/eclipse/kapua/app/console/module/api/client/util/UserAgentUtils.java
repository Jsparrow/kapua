/*******************************************************************************
 * Copyright (c) 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.console.module.api.client.util;

import org.apache.commons.lang3.StringUtils;

public class UserAgentUtils {

    private UserAgentUtils() {
    }

    public static boolean isIE() {
        return StringUtils.contains(getUserAgent().toLowerCase(), "msie");
    }

    public static boolean isFirefox() {
        return StringUtils.contains(getUserAgent().toLowerCase(), "firefox");
    }

    public static boolean isChrome() {
        return StringUtils.contains(getUserAgent().toLowerCase(), "chrome");
    }

    public static boolean isSafari() {
        /**
         * NOTE:
         *
         * Chrome user agent string is something like:
         *
         * Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36
         *
         * So even Chrome Browser would be recognized as Safari.
         * We need double check that if it contains "safari" it doesn't contains also "chrome"
         */

        return StringUtils.contains(getUserAgent(), "safari") && !StringUtils.contains(getUserAgent(), "chrome");
    }

    public static int getIEDocumentMode() {
        return getIeUserAgentAgent();
    }

    public static native String getUserAgent() /*-{
                                               return navigator.userAgent.toLowerCase();
                                               }-*/;

    public static native int getIeUserAgentAgent() /*-{
                                                   var myNav = navigator.userAgent.toLowerCase();
                                                   var ieVersion = (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : 0;
                                                   if (ieVersion > 0) {
                                                   return Math.min(ieVersion, document.documentMode);
                                                   }
                                                   return ieVersion;
                                                   }-*/;
}
