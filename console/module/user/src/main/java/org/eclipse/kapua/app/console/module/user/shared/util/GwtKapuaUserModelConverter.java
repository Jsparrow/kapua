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
package org.eclipse.kapua.app.console.module.user.shared.util;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.kapua.app.console.module.api.shared.util.GwtKapuaCommonsModelConverter;
import org.eclipse.kapua.app.console.module.user.shared.model.GwtUser.GwtUserStatus;
import org.eclipse.kapua.app.console.module.user.shared.model.GwtUserQuery;
import org.eclipse.kapua.model.query.SortOrder;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.model.KapuaEntity;
import org.eclipse.kapua.model.query.predicate.AndPredicate;
import org.eclipse.kapua.model.query.predicate.AttributePredicate.Operator;
import org.eclipse.kapua.service.user.UserAttributes;
import org.eclipse.kapua.service.user.UserFactory;
import org.eclipse.kapua.service.user.UserQuery;
import org.eclipse.kapua.service.user.UserStatus;

/**
 * Utility class for convertKapuaId {@link BaseModel}s to {@link KapuaEntity}ies and other Kapua models
 */
public class GwtKapuaUserModelConverter {

    private GwtKapuaUserModelConverter() {
    }

    /**
     * Converts a {@link GwtUserQuery} into a {@link UserQuery} object for backend usage
     *
     * @param loadConfig   the load configuration
     * @param gwtUserQuery the {@link GwtUserQuery} to convertKapuaId
     * @return the converted {@link UserQuery}
     */
    public static UserQuery convertUserQuery(PagingLoadConfig loadConfig, GwtUserQuery gwtUserQuery) {

        // Get Services
        KapuaLocator locator = KapuaLocator.getInstance();
        UserFactory userFactory = locator.getFactory(UserFactory.class);

        UserQuery query = userFactory.newQuery(GwtKapuaCommonsModelConverter.convertKapuaId(gwtUserQuery.getScopeId()));

        AndPredicate predicate = query.andPredicate();
        if (gwtUserQuery.getName() != null && !StringUtils.isEmpty(gwtUserQuery.getName())) {
            predicate.and(query.attributePredicate(UserAttributes.NAME, gwtUserQuery.getName(), Operator.LIKE));
        }
        if (gwtUserQuery.getUserStatus() != null && !gwtUserQuery.getUserStatus().equals(GwtUserStatus.ANY.toString())) {
            predicate.and(query.attributePredicate(UserAttributes.STATUS, convertUserStatus(gwtUserQuery.getUserStatus()), Operator.EQUAL));
        }
        if (gwtUserQuery.getPhoneNumber() != null && !StringUtils.isEmpty(gwtUserQuery.getPhoneNumber())) {
            predicate.and(query.attributePredicate(UserAttributes.PHONE_NUMBER, gwtUserQuery.getPhoneNumber(), Operator.LIKE));
        }
        if (gwtUserQuery.getExpirationDate() != null) {
            predicate.and(query.attributePredicate(UserAttributes.EXPIRATION_DATE, gwtUserQuery.getExpirationDate(), Operator.EQUAL));
        }
        if (gwtUserQuery.getEmail() != null && !StringUtils.isEmpty(gwtUserQuery.getEmail())) {
            predicate.and(query.attributePredicate(UserAttributes.EMAIL, gwtUserQuery.getEmail(), Operator.LIKE));
        }
        if (gwtUserQuery.getDisplayName() != null && !StringUtils.isEmpty(gwtUserQuery.getDisplayName())) {
            predicate.and(query.attributePredicate(UserAttributes.DISPLAY_NAME, gwtUserQuery.getDisplayName(), Operator.LIKE));
        }
        query.setOffset(loadConfig.getOffset());
        query.setLimit(loadConfig.getLimit());
        String sortField = StringUtils.isEmpty(loadConfig.getSortField()) ? UserAttributes.NAME : loadConfig.getSortField();
        if ("username".equals(sortField)) {
            sortField = UserAttributes.NAME;
        } else if ("modifiedByName".equals(sortField)) {
            sortField = UserAttributes.MODIFIED_BY;
        } else if ("expirationDateFormatted".equals(sortField)) {
            sortField = UserAttributes.EXPIRATION_DATE;
        } else if ("modifiedOnFormatted".equals(sortField)) {
            sortField = UserAttributes.MODIFIED_ON;
        } else if ("createdOnFormatted".equals(sortField)) {
            sortField = UserAttributes.CREATED_ON;
        } else if ("createdByName".equals(sortField)) {
            sortField = UserAttributes.CREATED_BY;
        }
        SortOrder sortOrder = loadConfig.getSortDir() == SortDir.DESC ? SortOrder.DESCENDING : SortOrder.ASCENDING;
        query.setPredicate(predicate);
        //
        // Return converted
        return query;
    }

    private static UserStatus convertUserStatus(String userStatus) {
        return UserStatus.valueOf(userStatus);
    }

    public static UserStatus convertUserStatus(GwtUserStatus gwtUserStatus) {
        return UserStatus.valueOf(gwtUserStatus.toString());
    }

}
