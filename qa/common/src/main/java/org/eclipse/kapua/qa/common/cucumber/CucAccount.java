/*******************************************************************************
 * Copyright (c) 2016, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kapua.qa.common.cucumber;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

/**
 * Data object used in Gherkin to transfer Account data.
 */
public class CucAccount {

    private static final Logger logger = LoggerFactory.getLogger(CucAccount.class);

	private String name;

    private BigInteger scopeId;

    private String expirationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getScopeId() {
        return scopeId;
    }

    public void setScopeId(BigInteger scopeId) {
        this.scopeId = scopeId;
    }

    public void setExpirationDate(String date) {
        this.expirationDate = date;
    }

    public Date getExpirationDate() {
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date expDate = null;
        Instant now = Instant.now();

        if (expirationDate == null) {
            return null;
        }
        // Special keywords for date
        switch (StringUtils.lowerCase(expirationDate.trim())) {
            case "yesterday":
                expDate = Date.from(now.minus(Duration.ofDays(1)));
                break;
            case "today":
                expDate = Date.from(now);
                break;
            case "tomorrow":
                expDate = Date.from(now.plus(Duration.ofDays(1)));
                break;
        }
        // Just parse date
        try {
            expDate = df.parse(StringUtils.lowerCase(expirationDate.trim()));
        } catch (ParseException | NullPointerException e) {
			logger.error(e.getMessage(), e);
            // skip, leave date null
        }

        return expDate;
    }
}
