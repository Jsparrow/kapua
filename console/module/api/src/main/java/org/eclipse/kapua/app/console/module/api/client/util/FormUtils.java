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

import java.util.List;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.MultiField;

public class FormUtils {

    private FormUtils() {
    }

    public static boolean isDirty(FieldSet fieldSet) {
        List<Component> fields = fieldSet.getItems();
        for (Component field1 : fields) {
            if (field1 instanceof MultiField) {
                MultiField<?> multiField = (MultiField<?>) field1;
                for (Field<?> field : multiField.getAll()) {
                    if (field.isRendered() && field.isDirty() && !(field.getValue() == null && field.getOriginalValue().equals(""))) {
                        return true;
                    }
                }
            } else if (field1 instanceof Field) {
                Field<?> field = (Field<?>) field1;
                if (field.isRendered() && field.isDirty() && !(field.getValue() == null && field.getOriginalValue().equals(""))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(FieldSet fieldSet) {
        List<Component> fields = fieldSet.getItems();
        for (Component field1 : fields) {
            if (field1 instanceof Field) {
                Field<?> field = (Field<?>) field1;
                if (field.isRendered() && !field.isValid()) {
                    return false;
                }
            }
        }
        return true;
    }
}
