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
package org.eclipse.kapua.commons.service.event.store.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.kapua.commons.service.event.store.api.EventStoreXmlRegistry;
import org.eclipse.kapua.event.ServiceEvent.EventStatus;
import org.eclipse.kapua.model.KapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdAdapter;
import org.eclipse.kapua.model.xml.DateXmlAdapter;

@XmlRootElement(name = "eventStoreRecord")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "contextId",
        "timestamp",
        "userId",
        "service",
        "entityType",
        "scopeId",
        "entityId",
        "operation",
        "inputs",
        "outputs",
        "status",
        "note",
}, //
        factoryClass = EventStoreXmlRegistry.class, //
        factoryMethod = "newEventStoreRecord")
public interface EventStoreRecord extends KapuaUpdatableEntity {

    String TYPE = "eventStoreRecord";

    @Override
	default String getType() {
        return TYPE;
    }

    @XmlElement(name = "contextId") String getContextId();

    void setContextId(String contextId);

    @XmlElement(name = "timestamp")
    @XmlJavaTypeAdapter(DateXmlAdapter.class) Date getTimestamp();

    void setTimestamp(Date timestamp);

    @XmlElement(name = "userId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class) KapuaId getUserId();

    void setUserId(KapuaId userId);

    @XmlElement(name = "service") String getService();

    void setService(String service);

    @XmlElement(name = "entityType") String getEntityType();

    void setEntityType(String entityType);

    @Override
	@XmlElement(name = "scopeId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class) KapuaId getScopeId();

    @Override void setScopeId(KapuaId scopeId);

    @XmlElement(name = "entityId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class) KapuaId getEntityId();

    void setEntityId(KapuaId entityId);

    @XmlElement(name = "operation") String getOperation();

    void setOperation(String operation);

    @XmlElement(name = "inputs") String getInputs();

    void setInputs(String inputs);

    @XmlElement(name = "outputs") String getOutputs();

    void setOutputs(String outputs);

    @XmlElement(name = "status") EventStatus getStatus();

    void setStatus(EventStatus status);

    @XmlElement(name = "note") String getNote();

    void setNote(String note);

}