package com.samplecompany.administration.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notifier-gateway")
public class NotifierGateway {

    private String url;
    private Integer maxComputerCount;
}
