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
package org.eclipse.kapua.kura.simulator.generator;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Generator {

    Payload update(Instant timestamp);

    static Generator onlyMetrics(final Function<Instant, Map<String, Object>> metrics) {
        Objects.requireNonNull(metrics);

        return (final Instant timestamp) -> new Payload(metrics.apply(timestamp));
    }
}