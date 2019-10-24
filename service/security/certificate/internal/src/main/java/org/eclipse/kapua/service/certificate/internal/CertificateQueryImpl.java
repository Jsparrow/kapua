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
package org.eclipse.kapua.service.certificate.internal;

import org.eclipse.kapua.commons.model.query.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.certificate.Certificate;
import org.eclipse.kapua.service.certificate.CertificateQuery;

public class CertificateQueryImpl extends AbstractKapuaQuery<Certificate> implements CertificateQuery {

    private Boolean includeInherited = Boolean.FALSE;

    /**
     * Constructor.
     *
     * @param scopeId The scopeId of the {@link KapuaQuery}
     */
    public CertificateQueryImpl(KapuaId scopeId) {
        this();
        setScopeId(scopeId);
    }

	/**
     * Constructor
     * <p>
     * This deeply clones the given {@link CertificateQuery}
     *
     * @param query the query to clone
     */
    public CertificateQueryImpl(KapuaQuery query) {
        super(query);
    }

	/**
     * Constructor
     */
    private CertificateQueryImpl() {
    }

	@Override
    public Boolean getIncludeInherited() {
        return includeInherited;
    }

	@Override
    public void setIncludeInherited(Boolean includeInherited) {
        this.includeInherited = includeInherited;
    }
}
