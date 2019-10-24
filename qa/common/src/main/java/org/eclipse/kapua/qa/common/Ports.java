/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.qa.common;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ports {

    private static final Logger logger = LoggerFactory.getLogger(Ports.class);

	private Ports() {
    }

    public static boolean isPortOpen(int port) throws IOException {
        try (final ServerSocket socket = new ServerSocket(port)) {
            return false;
        } catch (BindException e) {
            logger.error(e.getMessage(), e);
			return true;
        }
    }
}
