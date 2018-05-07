/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.authorization.access;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.KapuaDomainService;
import org.eclipse.kapua.service.KapuaEntityService;

/**
 * {@link AccessRole} service definition.
 *
 * @since 1.0.0
 */
public interface AccessRoleService extends KapuaEntityService<AccessRole, AccessRoleCreator>, KapuaDomainService<AccessInfoDomain> {

    AccessInfoDomain ACCESS_INFO_DOMAIN = new AccessInfoDomain();

    @Override
    default AccessInfoDomain getServiceDomain() {
        return ACCESS_INFO_DOMAIN;
    }

    /**
     * Creates a new {@link AccessRole} based on the parameters provided in the {@link AccessRoleCreator}.<br>
     * {@link AccessRole} must have a unique name within the scope.
     *
     * @param accessRoleCreator The creator object from which to create the {@link AccessRole}.
     * @throws KapuaException
     * @since 1.0.0
     */
    @Override
    AccessRole create(AccessRoleCreator accessRoleCreator) throws KapuaException;

    /**
     * Finds the {@link AccessRole} by scope identifier and {@link AccessRole} id.
     *
     * @param scopeId      The scope id in which to search.
     * @param accessRoleId The {@link AccessRole} id to search.
     * @return The {@link AccessRole} found or {@code null} if no entity was found.
     * @throws KapuaException
     * @since 1.0.0
     */
    @Override
    AccessRole find(KapuaId scopeId, KapuaId accessRoleId) throws KapuaException;

    /**
     * Finds the {@link AccessRole}s by scope identifier and {@link AccessInfo} id.
     *
     * @param scopeId      The scope id in which to search.
     * @param accessInfoId The {@link AccessInfo} id to search.
     * @return The {@link AccessRole}s related to the {@link AccessInfo} id.
     * @throws KapuaException
     * @since 1.0.0
     */
    AccessRoleListResult findByAccessInfoId(KapuaId scopeId, KapuaId accessInfoId) throws KapuaException;

    /**
     * Returns the {@link AccessRoleListResult} with elements matching the provided query.
     *
     * @param query The {@link AccessRoleQuery} used to filter results.
     * @return The {@link AccessRoleListResult} with elements matching the query parameter.
     * @throws KapuaException
     * @since 1.0.0
     */
    @Override
    AccessRoleListResult query(KapuaQuery<AccessRole> query) throws KapuaException;

    /**
     * Returns the count of the {@link AccessRole} elements matching the provided query.
     *
     * @param query The {@link AccessRoleQuery} used to filter results.
     * @return The count of the {@link AccessRole} elements matching the provided query.
     * @throws KapuaException
     * @since 1.0.0
     */
    @Override
    long count(KapuaQuery<AccessRole> query) throws KapuaException;

    /**
     * Delete the {@link AccessRole} by scope id and {@link AccessRole} id.
     *
     * @param scopeId      The scope id in which to delete.
     * @param accessRoleId The {@link AccessRole} id to delete.
     * @throws KapuaException
     * @since 1.0.0
     */
    @Override
    void delete(KapuaId scopeId, KapuaId accessRoleId) throws KapuaException;

}
