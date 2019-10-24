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
package org.eclipse.kapua.broker.core.router;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.eclipse.kapua.commons.setting.system.SystemSetting;

import java.util.HashMap;

public class PlaceholderReplacer {

    private static final String REGEX_REPLACEMENT_CHARS = "([\\\\\\.\\)\\]\\}\\(‌​\\[\\{\\*\\+\\?\\^\\$\\|])";
	private static HashMap<String, String> replacingMap;

	static {
        replacingMap = new HashMap<>();
        replacingMap.put(CAMEL_ROUTER_PLACEHOLDER.CLASSIFIER.name(), new StringBuilder().append("^").append(SystemSetting.getInstance().getMessageClassifier().replaceAll(REGEX_REPLACEMENT_CHARS, "\\\\$0")).append("\\.").toString());
    }

	private PlaceholderReplacer() {
    }

	public static String replace(String str) {
        return StrSubstitutor.replace(str, replacingMap);
    }

	enum CAMEL_ROUTER_PLACEHOLDER {
        CLASSIFIER
    }

}
