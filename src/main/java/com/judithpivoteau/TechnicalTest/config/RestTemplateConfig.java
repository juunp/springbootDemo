package com.judithpivoteau.TechnicalTest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${postservice.host}")
    private String host;

    @Bean
    public RestTemplate restTemplate (RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.rootUri(host).build();
        return restTemplate;
    }
}

