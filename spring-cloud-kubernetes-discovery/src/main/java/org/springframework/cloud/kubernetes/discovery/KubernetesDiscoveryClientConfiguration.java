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

import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KubernetesDiscoveryProperties.class)
@ConditionalOnProperty(value = "spring.cloud.kubernetes.discovery.enabled", matchIfMissing = true)
public class KubernetesDiscoveryClientConfiguration {
    private static Logger log = LoggerFactory.getLogger(KubernetesDiscoveryClientConfiguration.class);

    @Bean
    public KubernetesDiscoveryClient kubernetesDiscoveryClient(KubernetesClient client, KubernetesDiscoveryProperties properties) {
        log.info("kubernetes.client: " + client);
        return new KubernetesDiscoveryClient(client, properties);
    }

    @Bean
    public KubernetesAutoServiceRegistration kubernetesAutoServiceRegistration(KubernetesServiceRegistry registry,
                                                                               KubernetesDiscoveryClient kubernetesDiscoveryClient,
                                                                               KubernetesDiscoveryProperties properties) {
        return new KubernetesAutoServiceRegistration(registry, kubernetesDiscoveryClient, properties);
    }

    @Bean
    public KubernetesServiceRegistry kubernetesServiceRegistry() {
        return new KubernetesServiceRegistry();
    }
}
