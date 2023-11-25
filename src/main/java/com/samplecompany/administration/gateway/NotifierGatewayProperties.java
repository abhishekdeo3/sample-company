package com.samplecompany.administration.gateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notifier-gateway")
public class NotifierGatewayProperties {

    private String url;
    private Integer maxComputerCount;
}
