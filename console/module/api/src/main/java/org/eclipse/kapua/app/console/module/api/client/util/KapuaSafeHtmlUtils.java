/*******************************************************************************
 * Copyright (c) 2017, 2019 Eurotech and/or its affiliates and others
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

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import org.apache.commons.lang3.StringUtils;

public class KapuaSafeHtmlUtils {

    private KapuaSafeHtmlUtils() {
    }

    public static String htmlEscape(String unsafeHtml) {
        if (unsafeHtml == null) {
            return null;
        }
        return SafeHtmlUtils.htmlEscape(unsafeHtml);
    }

    public static String htmlUnescape(String safeHtml) {
        if (safeHtml == null) {
            return null;
        }

        if (StringUtils.contains(safeHtml, "&amp;")) {
            safeHtml = safeHtml.replace("&amp;", "&");
        }
        if (StringUtils.contains(safeHtml, "amp;")) {
            safeHtml = safeHtml.replace("amp;","");
        }
        if (StringUtils.contains(safeHtml, "&lt;")) {
            safeHtml = safeHtml.replace("&lt;", "<");
        }
        if (StringUtils.contains(safeHtml, "&gt;")) {
            safeHtml = safeHtml.replace("&gt;", ">");
        }
        if (StringUtils.contains(safeHtml, "&quot;")) {
            safeHtml = safeHtml.replace("&quot;", "\"");
        }
        if (StringUtils.contains(safeHtml, "&#39;")) {
            safeHtml = safeHtml.replace("&#39;", "'");
        }
        return safeHtml;
    }
}
