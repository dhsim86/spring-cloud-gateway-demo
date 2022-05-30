package com.dongho.demo.springcloudgatewaydemo.config.circuitbreaker;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.mockito.Mockito.*;

public class Resilience4JCircuitBreakerTest {

    public interface RemoteService {
        int process(int i);
    }

    private RemoteService remoteService;

    @BeforeEach
    public void beforeEach() {
        remoteService = Mockito.mock(RemoteService.class);
    }

    @Test
    public void basicTest() {
        // given
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .ringBufferSizeInClosedState(5)
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("test");
        Function<Integer, Integer> decoreated = CircuitBreaker.decorateFunction(circuitBreaker, remoteService::process);

        when(remoteService.process(anyInt())).thenThrow(new RuntimeException());

        // when
        for (int i = 0; i < 10; i++) {
            try {
                decoreated.apply(i);
            } catch (Exception ignore) {

            }
        }

        // then
        verify(remoteService, times(5)).process(anyInt());

    }

}
