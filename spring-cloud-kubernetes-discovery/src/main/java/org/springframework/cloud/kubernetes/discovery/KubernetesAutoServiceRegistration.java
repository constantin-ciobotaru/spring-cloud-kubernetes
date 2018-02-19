/*
 *   Copyright (C) 2016 to the original authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.springframework.cloud.kubernetes.discovery;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;

public class KubernetesAutoServiceRegistration extends AbstractAutoServiceRegistration<KubernetesServiceInstance> {
    private static Logger log = LoggerFactory.getLogger(KubernetesAutoServiceRegistration.class);

    private KubernetesDiscoveryClient discoveryClient;

    private KubernetesDiscoveryProperties properties;

    private AtomicBoolean running = new AtomicBoolean(false);

    public KubernetesAutoServiceRegistration(KubernetesServiceRegistry registry,
                                             KubernetesDiscoveryClient discoveryClient,
                                             KubernetesDiscoveryProperties properties) {
        super(registry);
        this.discoveryClient = discoveryClient;
        this.properties = properties;
    }

    /**
     * @return the object used to configure the registration
     */
    @Override
    protected Object getConfiguration() {
        return properties;
    }

    /**
     * @return true, if this is enabled
     */
    @Override
    protected boolean isEnabled() {
        return properties.isEnabled();
    }

    @Override
    protected KubernetesServiceInstance getRegistration() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        log.info("kubernetes.service.instance: " + serviceInstance);
        KubernetesServiceInstance registration = serviceInstance instanceof KubernetesServiceInstance ?(KubernetesServiceInstance) serviceInstance : null;
        return registration;
    }

    @Override
    protected KubernetesServiceInstance getManagementRegistration() {
        return null;
    }
}
