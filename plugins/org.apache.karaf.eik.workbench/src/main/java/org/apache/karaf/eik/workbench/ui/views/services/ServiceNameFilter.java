/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.karaf.eik.workbench.ui.views.services;

import org.apache.karaf.eik.workbench.provider.RuntimeDataProvider;
import org.apache.karaf.eik.workbench.provider.ServiceItem;
import org.apache.karaf.eik.workbench.ui.views.PropertyEntry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ServiceNameFilter extends ViewerFilter {

    private String serviceName;

    public ServiceNameFilter() {
        this("");
    }

    public ServiceNameFilter(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        boolean result;

        if (element instanceof RuntimeDataProvider) {
            result = true;
        } else if (element instanceof PropertyEntry) {
            result = true;
        }

        else if (element instanceof ServiceItem) {
            result = false;

            final String[] interfaces = ((ServiceItem) element).getServiceInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (interfaces[i].toLowerCase().indexOf(serviceName.toLowerCase()) > -1) {
                    result = true;
                    break;
                }
            }
        } else {
            result = false;
        }

        return result;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
