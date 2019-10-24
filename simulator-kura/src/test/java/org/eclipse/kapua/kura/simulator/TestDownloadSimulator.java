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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.kapua.kura.simulator.app.deploy.DownloadSimulator;
import org.eclipse.scada.utils.concurrent.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDownloadSimulator {

    private static final Logger logger = LoggerFactory.getLogger(TestDownloadSimulator.class);

	private TestDownloadSimulator() {
    }

    public static void main(final String[] args) throws Exception {
        final ScheduledExecutorService executor = Executors
                .newSingleThreadScheduledExecutor(new NamedThreadFactory("DownloadSimulator"));

        try (final DownloadSimulator sim = new DownloadSimulator(executor, 10 * 1024)) {
            sim.startDownload(1, 1024 * 1024, System.out::println, () -> logger.info("Download completed!"));
            Thread.sleep(10_000);
            sim.cancelDownload();
            logger.info(String.valueOf(sim.getState()));
            Thread.sleep(120_000);
        }

        executor.shutdown();
    }
}
