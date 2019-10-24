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
package org.eclipse.kapua.app.console.module.account.shared.util;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.kapua.app.console.module.account.shared.model.GwtAccountQuery;
import org.eclipse.kapua.app.console.module.api.shared.util.GwtKapuaCommonsModelConverter;
import org.eclipse.kapua.model.query.FieldSortCriteria;
import org.eclipse.kapua.model.query.SortOrder;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.model.query.predicate.AndPredicate;
import org.eclipse.kapua.model.query.predicate.AttributePredicate.Operator;
import org.eclipse.kapua.service.account.AccountAttributes;
import org.eclipse.kapua.service.account.AccountFactory;
import org.eclipse.kapua.service.account.AccountQuery;

public class GwtKapuaAccountModelConverter {

    private GwtKapuaAccountModelConverter() {
    }

    public static AccountQuery convertAccountQuery(PagingLoadConfig loadConfig, GwtAccountQuery gwtAccountQuery) {
        KapuaLocator locator = KapuaLocator.getInstance();
        AccountFactory factory = locator.getFactory(AccountFactory.class);

        AccountQuery query = factory.newQuery(GwtKapuaCommonsModelConverter.convertKapuaId(gwtAccountQuery.getScopeId()));

        AndPredicate predicate = query.andPredicate();

        if (gwtAccountQuery.getName() != null && !StringUtils.isEmpty(gwtAccountQuery.getName().trim())) {
            predicate.and(query.attributePredicate(AccountAttributes.NAME, gwtAccountQuery.getName(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationName() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationName())) {
            predicate.and(query.attributePredicate(AccountAttributes.ORGANIZATION_NAME, gwtAccountQuery.getOrganizationName(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationEmail() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationEmail())) {
            predicate.and(query.attributePredicate(AccountAttributes.ORGANIZATION_EMAIL, gwtAccountQuery.getOrganizationEmail(), Operator.LIKE));
        }

        if (gwtAccountQuery.getExpirationDate() != null) {
            predicate.and(query.attributePredicate(AccountAttributes.EXPIRATION_DATE, gwtAccountQuery.getExpirationDate(), Operator.EQUAL));
        }

        if (gwtAccountQuery.getOrganizationContactName() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationContactName())) {
            predicate.and(query.attributePredicate(AccountAttributes.CONTACT_NAME, gwtAccountQuery.getOrganizationContactName(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationPhoneNumber() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationPhoneNumber())) {
            predicate.and(query.attributePredicate(AccountAttributes.PHONE_NUMBER, gwtAccountQuery.getOrganizationPhoneNumber(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationAddressLine1() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationAddressLine1())) {
            predicate.and(query.attributePredicate(AccountAttributes.ADDRESS_1, gwtAccountQuery.getOrganizationAddressLine1(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationAddressLine2() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationAddressLine2())) {
            predicate.and(query.attributePredicate(AccountAttributes.ADDRESS_2, gwtAccountQuery.getOrganizationAddressLine2(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationZipPostCode() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationZipPostCode())) {
            predicate.and(query.attributePredicate(AccountAttributes.ZIP_POST_CODE, gwtAccountQuery.getOrganizationZipPostCode(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationCity() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationCity())) {
            predicate.and(query.attributePredicate(AccountAttributes.ORGANIZATION_CITY, gwtAccountQuery.getOrganizationCity(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationStateProvinceCountry() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationStateProvinceCountry())) {
            predicate.and(query.attributePredicate(AccountAttributes.STATE_PROVINCE, gwtAccountQuery.getOrganizationStateProvinceCountry(), Operator.LIKE));
        }

        if (gwtAccountQuery.getOrganizationCountry() != null && !StringUtils.isEmpty(gwtAccountQuery.getOrganizationCountry())) {
            predicate.and(query.attributePredicate(AccountAttributes.ORGANIZATION_COUNTRY, gwtAccountQuery.getOrganizationCountry(), Operator.LIKE));
        }

        String sortField = StringUtils.isEmpty(loadConfig.getSortField()) ? AccountAttributes.NAME : loadConfig.getSortField();
        if ("modifiedOnFormatted".equals(sortField)) {
            sortField = AccountAttributes.MODIFIED_ON;
        } else if ("modifiedByName".equals(sortField)) {
            sortField = AccountAttributes.MODIFIED_BY;
        }
        SortOrder sortOrder = loadConfig.getSortDir() == SortDir.DESC ? SortOrder.DESCENDING : SortOrder.ASCENDING;
        FieldSortCriteria sortCriteria = query.fieldSortCriteria(sortField, sortOrder);
        query.setSortCriteria(sortCriteria);
        query.setOffset(loadConfig.getOffset());
        query.setLimit(loadConfig.getLimit());
        query.setPredicate(predicate);

        return query;
    }
}
