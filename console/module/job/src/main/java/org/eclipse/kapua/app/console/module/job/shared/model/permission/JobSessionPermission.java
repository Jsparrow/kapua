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
package org.eclipse.kapua.app.console.module.job.shared.model.permission;

import org.eclipse.kapua.app.console.module.api.shared.model.session.GwtSessionPermission;
import org.eclipse.kapua.app.console.module.api.shared.model.session.GwtSessionPermissionAction;
import org.eclipse.kapua.app.console.module.api.shared.model.session.GwtSessionPermissionScope;

public class JobSessionPermission extends GwtSessionPermission {

    protected JobSessionPermission() {
    }

    private JobSessionPermission(GwtSessionPermissionAction action) {
        this(action, GwtSessionPermissionScope.SELF);
    }

    private JobSessionPermission(GwtSessionPermissionAction action, GwtSessionPermissionScope scope) {
        super("job", action, scope);
    }

    public static JobSessionPermission read() {
        return new JobSessionPermission(GwtSessionPermissionAction.read);
    }

    public static JobSessionPermission write() {
        return new JobSessionPermission(GwtSessionPermissionAction.write);
    }

    public static JobSessionPermission delete() {
        return new JobSessionPermission(GwtSessionPermissionAction.delete);
    }

    public static JobSessionPermission deleteAll() {
        return new JobSessionPermission(GwtSessionPermissionAction.delete, GwtSessionPermissionScope.ALL);
    }

    public static JobSessionPermission execute() {
        return new JobSessionPermission(GwtSessionPermissionAction.execute);
    }
}
