package com.dongho.demo.springcloudgatewaydemo.config.filter;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public static class Config {

    }

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("LoggingFilter before: request id => {}", request.getId());
            log.info("LoggingFilter before: request uri => {}", request.getURI());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);

                log.info("LoggingFilter after: routed uri => {}", uri.toString());
                log.info("LoggingFilter after: response code => {}", response.getStatusCode());
            }));
        });
    }

}
