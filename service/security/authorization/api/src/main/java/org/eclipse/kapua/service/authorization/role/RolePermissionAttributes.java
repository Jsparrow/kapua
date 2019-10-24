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
package org.eclipse.kapua.service.authorization.role;

import org.eclipse.kapua.model.KapuaEntityAttributes;
import org.eclipse.kapua.service.authorization.permission.Permission;
import org.eclipse.kapua.service.authorization.permission.PermissionAttributes;

/**
 * Query predicate attribute names for {@link RolePermission} entity.
 *
 * @since 1.0.0
 */
public class RolePermissionAttributes extends KapuaEntityAttributes {

    /**
     * Predicate for field {@link RolePermission#getRoleId()}
     *
     * @since 1.0.0
     */
    public static final String ROLE_ID = "roleId";

    /**
     * Predicate for field {@link RolePermission#getPermission()}
     *
     * @since 1.0.0
     */
    public static final String PERMISSION = "permission";

    /**
     * Predicate for field {@link RolePermission#getPermission()}.{@link Permission#getDomain()}
     *
     * @since 1.0.0
     */
    public static final String PERMISSION_DOMAIN = new StringBuilder().append(PERMISSION).append(".").append(PermissionAttributes.DOMAIN).toString();

    /**
     * Predicate for field {@link RolePermission#getPermission()}.{@link Permission#getAction()}
     *
     * @since 1.0.0
     */
    public static final String PERMISSION_ACTION = new StringBuilder().append(PERMISSION).append(".").append(PermissionAttributes.ACTION).toString();

    /**
     * Predicate for field {@link RolePermission#getPermission()}.{@link Permission#getTargetScopeId()}
     *
     * @since 1.0.0
     */
    public static final String PERMISSION_TARGET_SCOPE_ID = new StringBuilder().append(PERMISSION).append(".").append(PermissionAttributes.TARGET_SCOPE_ID).toString();

    /**
     * Predicate for field {@link RolePermission#getPermission()}.{@link Permission#getGroupId()}
     *
     * @since 1.0.0
     */
    public static final String PERMISSION_GROUP_ID = new StringBuilder().append(PERMISSION).append(".").append(PermissionAttributes.GROUP_ID).toString();

    /**
     * Predicate for field {@link RolePermission#getPermission()}.{@link Permission#getForwardable()}
     *
     * @since 1.0.0
     */
    public static final String PERMISSION_FORWARDABLE = new StringBuilder().append(PERMISSION).append(".").append(PermissionAttributes.FORWARDABLE).toString();

}
