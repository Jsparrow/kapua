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
package org.eclipse.kapua.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KapuaDelayUtil {

    private static final Logger logger = LoggerFactory.getLogger(KapuaDelayUtil.class);

	private KapuaDelayUtil() {
    }

	public static void executeDelay(){
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
				//Restore the interupted status
                Thread.currentThread().interrupt();
            }
        }
}
