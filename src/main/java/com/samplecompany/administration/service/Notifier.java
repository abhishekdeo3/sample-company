package com.samplecompany.administration.service;

import com.samplecompany.administration.gateway.NotifierGatewayProperties;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

@Component
@Slf4j
public class Notifier {

    private final EmployeeRepository employeeRepository;

    private final NotifierGatewayProperties notifierGatewayProperties;

    private final WebClient webClient;

    public Notifier(EmployeeRepository employeeRepository, NotifierGatewayProperties notifierGatewayProperties,
                    @Qualifier("NotifierWebClient") WebClient webClient) {
        this.employeeRepository = employeeRepository;
        this.notifierGatewayProperties = notifierGatewayProperties;
        this.webClient = webClient;
    }

    @PostConstruct
    public void init() {

        List<EmployeeEntity> employeeEntities = employeeRepository
                .getAllEmployeesWithMoreThanThreeComputers(notifierGatewayProperties.getMaxComputerCount());

        if (!CollectionUtils.isEmpty(employeeEntities)) {

            for (EmployeeEntity employeeEntity : employeeEntities) {

                NotifierBody notifierBody = callNotifier(employeeEntity);
                log.warn("Employee {} has three or more computers assigned!", notifierBody.employeeAbbreviation);
            }
        }
    }

    public NotifierBody callNotifier(EmployeeEntity employeeEntity) {

        try {
            return webClient.post()
                    .uri(notifierGatewayProperties.getUrl())
                    .bodyValue(new NotifierBody("warning", employeeEntity.getAbbreviation(),
                            String.format("Employee %s has three or more computers assigned!",
                                    employeeEntity.getAbbreviation())))
                    .retrieve().bodyToMono(NotifierBody.class).block();
        } catch (WebClientException webClientException) {
            throw new RuntimeException("Error occurred while calling notifier service", webClientException);
        }
    }

    public record NotifierBody(
            String level,
            String employeeAbbreviation,
            String message
    ) {

    }
}
