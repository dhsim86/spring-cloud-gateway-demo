package com.dongho.demo.springcloudgatewaydemo.config.circuitbreaker;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResourceAccessException;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class GlobalCircuitBreakerConfig {

/*
	@Bean
	public CircuitBreakerConfig circuitBreakerConfig() {
		return CircuitBreakerConfig.custom()
			.slidingWindow(100, 50, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
			.failureRateThreshold(50)
			.waitDurationInOpenState(Duration.ofSeconds(2))
			.permittedNumberOfCallsInHalfOpenState(20)
			.recordExceptions(ResourceAccessException.class, IOException.class, TimeoutException.class)
			.build();
	}

	@Bean
	public TimeLimiterConfig timeLimiterConfig() {
		return TimeLimiterConfig.custom()
			.timeoutDuration(Duration.ofSeconds(5))
			.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
			.circuitBreakerConfig(circuitBreakerConfig())
			.timeLimiterConfig(timeLimiterConfig())
			.build());
	} */
/*
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> specificCustomConfiguration1() {
		return factory -> factory.configure(builder -> builder
				.circuitBreakerConfig(circuitBreakerConfig())
				.timeLimiterConfig(timeLimiterConfig())
				.build(),
			"myCircuitBreaker");
	}*/

}
