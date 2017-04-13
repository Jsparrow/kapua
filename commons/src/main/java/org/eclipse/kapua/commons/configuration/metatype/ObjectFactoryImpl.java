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
package org.eclipse.kapua.commons.configuration.metatype;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * ObjectFactory allows you to programmatically construct new instances of the Java representation for XML content.<br>
 * The Java representation of XML content can consist of schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 * @since 1.0
 */
@XmlRegistry
public class ObjectFactoryImpl {

    private final static QName _MetaData_QNAME = new QName("http://www.osgi.org/xmlns/metatype/v1.2.0", "MetaData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes.
     */
    public ObjectFactoryImpl() {
    }

    /**
     * Create an instance of {@link TiconImpl }
     */
    public TiconImpl createTicon() {
        return new TiconImpl();
    }

    /**
     * Create an instance of {@link TattributeImpl }
     */
    public TattributeImpl createTattribute() {
        return new TattributeImpl();
    }

    /**
     * Create an instance of {@link TmetadataImpl }
     */
    public TmetadataImpl createTmetadata() {
        return new TmetadataImpl();
    }

    /**
     * Create an instance of {@link TdesignateImpl }
     */
    public TdesignateImpl createTdesignate() {
        return new TdesignateImpl();
    }

    /**
     * Create an instance of {@link TadImpl }
     */
    public TadImpl createTad() {
        return new TadImpl();
    }

    /**
     * Create an instance of {@link TobjectImpl }
     */
    public TobjectImpl createTobject() {
        return new TobjectImpl();
    }

    /**
     * Create an instance of {@link TocdImpl }
     */
    public TocdImpl createTocd() {
        return new TocdImpl();
    }

    /**
     * Create an instance of {@link ToptionImpl }
     */
    public ToptionImpl createToption() {
        return new ToptionImpl();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TmetadataImpl }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.osgi.org/xmlns/metatype/v1.2.0", name = "MetaData")
    public JAXBElement<TmetadataImpl> createMetaData(TmetadataImpl value) {
        return new JAXBElement<TmetadataImpl>(_MetaData_QNAME, TmetadataImpl.class, null, value);
    }

}
