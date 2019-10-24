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
package org.eclipse.kapua.app.console.module.api.client.ui.tab;

import org.eclipse.kapua.app.console.module.api.client.resources.icons.KapuaIcon;

public class TabItem extends com.extjs.gxt.ui.client.widget.TabItem {

    private String originalText;
    private KapuaIcon icon;

    public TabItem(String text, KapuaIcon icon) {
        setText(text);
        setIcon(icon);
    }

    @Override
    public void setText(String text) {
        super.setText((icon != null ? icon.getInlineHTML() + "&nbsp;&nbsp;" : "") + text);
        this.originalText = text;
    }

    public void setIcon(KapuaIcon icon) {
        super.setText((icon != null ? icon.getInlineHTML() + "&nbsp;&nbsp;" : "") + originalText);
        this.icon = icon;
    }
}
