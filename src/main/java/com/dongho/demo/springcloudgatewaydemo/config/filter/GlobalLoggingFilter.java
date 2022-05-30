package com.dongho.demo.springcloudgatewaydemo.config.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalLoggingFilter extends AbstractGatewayFilterFactory<GlobalLoggingFilter.Config> {

    @Getter
    @Setter
    public static class Config {
        private String baseMessage;

        private boolean preLogger;
        private boolean postLogger;
    }

    public GlobalLoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("GlobalLoggingFilter baseMessage: {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("GlobalLoggingFilter before: request id => {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("GlobalLoggingFilter after: response code => {}", response.getStatusCode());
                }
            }));
        });
    }
}
