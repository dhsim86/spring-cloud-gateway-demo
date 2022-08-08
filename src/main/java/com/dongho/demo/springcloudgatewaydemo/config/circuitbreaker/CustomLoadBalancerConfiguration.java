package com.dongho.demo.springcloudgatewaydemo.config.circuitbreaker;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomLoadBalancerConfiguration {

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
		ConfigurableApplicationContext context) {
		return ServiceInstanceListSupplier.builder()
			.withDiscoveryClient()
			.withHealthChecks()
			.withZonePreference()
			.build(context);
	}

	// refer https://github.com/spring-cloud/spring-cloud-commons/issues/964
	@Bean
	public LoadBalancerClientSpecification loadBalancerClientSpecification() {
		return new LoadBalancerClientSpecification("default.customLoadBalancerConfiguration",
			new Class[]{CustomLoadBalancerConfiguration.class});
	}


}

