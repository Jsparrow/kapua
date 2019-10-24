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
package org.eclipse.kapua.commons.about;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Category(JUnitTests.class)
public class AboutTest {

    private static final Logger logger = LoggerFactory.getLogger(AboutTest.class);

	@Test
    public void testScan1() {
        final AboutScanner scanner = AboutScanner.scan();

        Assert.assertNotNull(scanner);
        Assert.assertNotNull(scanner.getEntries());
        Assert.assertFalse(scanner.getEntries().isEmpty());

        scanner.getEntries().forEach(entry -> logger.info(entry.getId()));
    }
}
