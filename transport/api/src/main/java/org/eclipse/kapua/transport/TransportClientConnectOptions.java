/*******************************************************************************
 * Copyright (c) 2016, 2019 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.transport;

import java.net.URI;

/**
 * Container for connection options of {@link TransportFacade}.
 *
 * @author alberto.codutti
 * @since 1.0.0
 */
public interface TransportClientConnectOptions {

    /**
     * Gets the id to use for the connection in the transport layer.
     *
     * @return The id to use.
     * @since 1.0.0
     */
    String getClientId();

    /**
     * Sets the id to be used for connection in the transport layer.
     *
     * @param clientId The id to use.
     * @since 1.0.0
     */
    void setClientId(String clientId);

    /**
     * Gets the username to use for the connection in the transport layer.
     *
     * @return The username to use.
     * @since 1.0.0
     */
    String getUsername();

    /**
     * Sets the username to be used for the connection in the transport layer.
     *
     * @param username The username to use.
     * @since 1.0.0
     */
    void setUsername(String username);

    /**
     * Gets the password to user for the connection in the transport layer.
     *
     * @return The password to use.
     * @since 1.0.0
     */
    char[] getPassword();

    /**
     * Sets the password to be used for the connection in the transport layer.
     *
     * @param password The password to use.
     * @since 1.0.0
     */
    void setPassword(char[] password);

    /**
     * Gets the endpoint URI to use for the connection in the transport layer.
     *
     * @return The endpoint URI to use.
     * @since 1.0.0
     */
    URI getEndpointURI();

    /**
     * Sets the endpint URI to be use for the connection in the transport layer.
     *
     * @param endpontURI The endpoint URI to use.
     * @since 1.0.0
     */
    void setEndpointURI(URI endpontURI);
}
