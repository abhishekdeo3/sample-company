package com.samplecompany.administration;

import com.samplecompany.administration.configuration.properties.NotifierGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {NotifierGateway.class})
public class SampleCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleCompanyApplication.class, args);
    }

}
