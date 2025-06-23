package dev.brenolucks.clicker_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final RestProperties restProperties;

    public WebClientConfig(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(restProperties.getHost())
                .build();
    }
}
