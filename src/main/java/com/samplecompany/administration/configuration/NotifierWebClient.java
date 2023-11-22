package com.samplecompany.administration.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class NotifierWebClient {

    @Bean
    @Qualifier("NotifierWebClient")
    public WebClient webClient() {

        log.info("Warming up Webclient");
        return WebClient.builder().build();
    }
}
