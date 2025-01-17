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
package org.eclipse.kapua.app.api.resources.v1.resources.model.message;

import org.eclipse.kapua.message.KapuaPayload;
import org.eclipse.kapua.message.xml.XmlAdaptedMetric;
import org.eclipse.kapua.model.type.ObjectValueConverter;
import org.eclipse.kapua.model.xml.BinaryXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "payload")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class JsonKapuaPayload {

    private List<XmlAdaptedMetric> metrics;
    private byte[] body;

    /**
     * Constructor
     */
    public JsonKapuaPayload() {
    }

    public JsonKapuaPayload(KapuaPayload payload) {

        setBody(payload.getBody());

        payload.getMetrics().forEach((metricName, metricValue) -> {
            XmlAdaptedMetric jsonMetric = new XmlAdaptedMetric();

            jsonMetric.setName(metricName);
            jsonMetric.setValueType(metricValue.getClass());
            jsonMetric.setValue(ObjectValueConverter.toString(metricValue));

            getMetrics().add(jsonMetric);
        });
    }

    @XmlElementWrapper(name = "metrics")
    @XmlElement(name = "metric")
    public List<XmlAdaptedMetric> getMetrics() {
        if (metrics == null) {
            metrics = new ArrayList<>();
        }

        return metrics;
    }

    public void setMetrics(List<XmlAdaptedMetric> metrics) {
        this.metrics = metrics;
    }

    @XmlElement(name = "body")
    @XmlJavaTypeAdapter(BinaryXmlAdapter.class)
    @ApiModelProperty(dataType = "string")
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

}
