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
package org.eclipse.kapua.event;

import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdAdapter;
import org.eclipse.kapua.model.xml.DateXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "serviceEvent")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id",
        "contextId",
        "timestamp",
        "userId",
        "service",
        "entityType",
        "scopeId",
        "entityScopeId",
        "entityId",
        "operation",
        "inputs",
        "outputs",
        "status",
        "note",
})
/**
 * Service event bus event object
 *
 * @since 1.0
 *
 */
public class ServiceEvent implements Serializable {

    private static final long serialVersionUID = -6642016486673574115L;

	@XmlElement(name = "id")
    private String id;

	@XmlElement(name = "contextId")
    private String contextId;

	@XmlElement(name = "timestamp")
    @XmlJavaTypeAdapter(DateXmlAdapter.class)
    private Date timestamp;

	@XmlElement(name = "userId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    private KapuaId userId;

	@XmlElement(name = "service")
    private String service;

	@XmlElement(name = "entityType")
    private String entityType;

	@XmlElement(name = "scopeId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    private KapuaId scopeId;

	@XmlElement(name = "entityScopeId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    private KapuaId entityScopeId;

	@XmlElement(name = "entityId")
    @XmlJavaTypeAdapter(KapuaIdAdapter.class)
    private KapuaId entityId;

	@XmlElement(name = "operation")
    private String operation;

	@XmlElement(name = "inputs")
    private String inputs;

	@XmlElement(name = "outputs")
    private String outputs;

	@XmlElement(name = "status")
    private EventStatus status;

	@XmlElement(name = "note")
    private String note;

	/**
     * Get the event id
     *
     * @return
     */
    public String getId() {
        return id;
    }

	/**
     * Set the event id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

	/**
     * Get the context id
     *
     * @return
     */
    public String getContextId() {
        return contextId;
    }

	/**
     * Set the context id
     *
     * @param contextId
     */
    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

	/**
     * Get the event timestamp (event triggered)
     *
     * @return
     */
    public Date getTimestamp() {
        return timestamp;
    }

	/**
     * Set the event timestamp (event triggered)
     *
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

	/**
     * Get the user id that fired the event
     *
     * @return
     */
    public KapuaId getUserId() {
        return userId;
    }

	/**
     * Set the user id that fired the event
     *
     * @param userId
     */
    public void setUserId(KapuaId userId) {
        this.userId = userId;
    }

	/**
     * Get the service name that fired the event
     *
     * @return
     */
    public String getService() {
        return service;
    }

	/**
     * Set the service name that fired the event
     *
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }

	/**
     * Get the entity type related to the fired event
     *
     * @return
     */
    public String getEntityType() {
        return entityType;
    }

	/**
     * Set the entity type related to the fired event
     *
     * @param entityType
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

	/**
     * Get the scope id of the user that fired the event
     *
     * @return
     */
    public KapuaId getScopeId() {
        return scopeId;
    }

	/**
     * Set the scope id of the user that fired the event
     *
     * @param scopeId
     */
    public void setScopeId(KapuaId scopeId) {
        this.scopeId = scopeId;
    }

	/**
     * Get the entity scope id related to the fired event
     *
     * @return
     */
    public KapuaId getEntityScopeId() {
        return entityScopeId;
    }

	/**
     * Set the entity scope id related to the fired event
     *
     * @param entityScopeId
     */
    public void setEntityScopeId(KapuaId entityScopeId) {
        this.entityScopeId = entityScopeId;
    }

	/**
     * Get the entity id related to the fired event
     *
     * @return
     */
    public KapuaId getEntityId() {
        return entityId;
    }

	/**
     * Set the entity id related to the fired event
     *
     * @param entityId
     */
    public void setEntityId(KapuaId entityId) {
        this.entityId = entityId;
    }

	/**
     * Get the operation that fired the event
     *
     * @return
     */
    public String getOperation() {
        return operation;
    }

	/**
     * Set the operation that fired the event
     *
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

	/**
     * Get the inputs
     *
     * @return
     */
    public String getInputs() {
        return inputs;
    }

	/**
     * Set the inputs
     *
     * @param inputs
     */
    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

	/**
     * Get the outputs
     *
     * @return
     */
    public String getOutputs() {
        return outputs;
    }

	/**
     * Set the outputs
     *
     * @param outputs
     */
    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

	/**
     * Get the event status
     *
     * @return
     */
    public EventStatus getStatus() {
        return status;
    }

	/**
     * Set the event status
     *
     * @param status
     */
    public void setStatus(EventStatus status) {
        this.status = status;
    }

	/**
     * Get the notes
     *
     * @return
     */
    public String getNote() {
        return note;
    }

	/**
     * Set the notes
     *
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

	/**
     * Event status
     */
    public enum EventStatus {
        /**
         * Event just triggered by the interceptor or explicitly generated by a service
         */
        TRIGGERED,
        /**
         * Send confirmed (so no exception got by the send layer)
         */
        SENT,
        /**
         * Send error (an error occurred during the event send step)
         */
        SEND_ERROR
    }

}