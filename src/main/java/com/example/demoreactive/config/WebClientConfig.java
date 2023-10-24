package com.example.demoreactive.config;

import com.example.demoreactive.services.ApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("https://test-lbk.free.beeceptor.com")
                .filter(createDynamicHeaderFilter())
                .build();
    }

    @Bean
    public ApiService apiService(WebClient webClient) {
        return new ApiService(webClient);
    }

    private ExchangeFilterFunction createDynamicHeaderFilter() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            return Mono.just(ClientRequest.from(request)
                    .header("x-custom-header", "x-custom-value-" +
                            System.currentTimeMillis())
                    .build());
        });
    }
}

