/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.kura.simulator;

import java.util.function.Consumer;

import org.eclipse.kapua.kura.simulator.payload.Message;
import org.eclipse.kapua.kura.simulator.topic.Topic;

public interface Transport {

    /**
     * Connect
     */
    void connect();

    /**
     * Disconnect gracefully <br>
     * A later call to {@link #connect()} must be possible.
     */
    void disconnect();

    /**
     * Set the runnable which will be called when a connection was established
     * <p>
     * <strong>Note:</strong> When the connection is already established then
     * this method won't be called again.
     * </p>
     */
    void whenConnected(Runnable runnable);

    /**
     * Set the runnable which will be called when a connection was lost
     */
    void whenDisconnected(Runnable runnable);

    /**
     * Subscribe to a topic or topic pattern
     *
     * @param topic
     *            the topic to subscribe to
     * @param consumer
     *            the consumer to call when a message was received
     */
    void subscribe(Topic topic, Consumer<Message> consumer);

    /**
     * Unsubscribe from a topic or topic pattern
     *
     * @param topic
     *            to unsubscribe from
     */
    void unsubscribe(Topic topic);

    /**
     * Send a message to a topic
     *
     * @param topic
     *            the topic to send the message to
     * @param payload
     *            the payload to send
     */
    void sendMessage(Topic topic, byte[] payload);
}
