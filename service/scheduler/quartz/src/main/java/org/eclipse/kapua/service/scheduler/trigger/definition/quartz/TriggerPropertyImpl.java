/*******************************************************************************
 * Copyright (c) 2019 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.scheduler.trigger.definition.quartz;


import org.eclipse.kapua.service.scheduler.trigger.definition.TriggerProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * {@link TriggerProperty} implementation.
 *
 * @since 1.0.0
 */
@Embeddable
public class TriggerPropertyImpl implements TriggerProperty {

    @Basic
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Basic
    @Column(name = "property_type", nullable = false, updatable = false)
    private String propertyType;

    @Basic
    @Column(name = "property_value", nullable = false, updatable = false)
    private String propertyValue;

    /**
     * Constructor
     *
     * @since 1.0.0
     */
    public TriggerPropertyImpl() {
    }

    /**
     * Constructor.
     *
     * @param name          The name.
     * @param propertyType  The property type.
     * @param propertyValue The property value.
     * @since 1.0.0
     */
    public TriggerPropertyImpl(String name, String propertyType, String propertyValue) {
        setName(name);
        setPropertyType(propertyType);
        setPropertyValue(propertyValue);
    }

	/**
     * Clone constructor.
     *
     * @param triggerProperty The {@link TriggerProperty} to clone.
     * @since 1.1.0
     */
    private TriggerPropertyImpl(TriggerProperty triggerProperty) {
        setName(triggerProperty.getName());
        setPropertyType(triggerProperty.getPropertyType());
        setPropertyValue(triggerProperty.getPropertyValue());
    }

	@Override
    public String getName() {
        return name;
    }

	@Override
    public void setName(String name) {
        this.name = name;
    }

	@Override
    public String getPropertyType() {
        return propertyType;
    }

	@Override
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

	@Override
    public String getPropertyValue() {
        return propertyValue;
    }

	@Override
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

	public static TriggerPropertyImpl parse(TriggerProperty triggerProperty) {
        return triggerProperty != null ? (triggerProperty instanceof TriggerPropertyImpl ? (TriggerPropertyImpl) triggerProperty : new TriggerPropertyImpl(triggerProperty)) : null;
    }
}
