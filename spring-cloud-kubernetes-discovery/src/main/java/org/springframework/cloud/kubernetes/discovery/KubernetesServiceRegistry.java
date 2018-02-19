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

import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

public class KubernetesServiceRegistry implements ServiceRegistry<KubernetesServiceInstance> {

    @Override
    public void register(KubernetesServiceInstance registration) {
    }

    @Override
    public void deregister(KubernetesServiceInstance registration) {

    }

    @Override
    public void close() {
    }

    @Override
    public void setStatus(KubernetesServiceInstance registration,
                          String status) {
    }

    @Override
    public <T> T getStatus(KubernetesServiceInstance registration) {
        return null;
    }
}
