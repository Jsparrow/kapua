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
package org.eclipse.kapua.model.query;

public interface FieldSortCriteria extends KapuaSortCriteria{
    /**
     * Get the sort attribute name
     *
     * @return
     */
    String getAttributeName();

    /**
     * Get the sort attribute order
     *
     * @return
     */
    SortOrder getSortOrder();
}
