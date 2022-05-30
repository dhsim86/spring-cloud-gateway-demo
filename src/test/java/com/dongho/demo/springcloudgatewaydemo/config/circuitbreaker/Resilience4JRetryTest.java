package com.dongho.demo.springcloudgatewaydemo.config.circuitbreaker;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class Resilience4JRetryTest {

    public interface RemoteService {
        int process(int i);
    }

    private Resilience4JCircuitBreakerTest.RemoteService remoteService;

    @BeforeEach
    public void beforeEach() {
        remoteService = Mockito.mock(Resilience4JCircuitBreakerTest.RemoteService.class);
    }

    @Test
    public void basicTest() {
        // given
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(2)
                .build();

        RetryRegistry registry = RetryRegistry.of(config);
        Retry retry = registry.retry("test");
        Function<Integer, Integer> decorated = Retry.decorateFunction(retry, (Integer i) -> remoteService.process(i));

        when(remoteService.process(anyInt())).thenThrow(new RuntimeException());

        // when
        try {
            decorated.apply(1);
        } catch (Exception ignore) {

        }

        // then
        verify(remoteService, times(2)).process(anyInt());
    }

}
