package com.samplecompany.administration.service;

import com.samplecompany.administration.gateway.NotifierGatewayProperties;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class NotifierTest {

    @Mock
    private WebClient webClient;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private NotifierGatewayProperties notifierGatewayProperties;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;


    private Notifier notifier;

    @BeforeEach
    void setUp() {
        notifier = new Notifier(employeeRepository, notifierGatewayProperties, webClient);
    }

    @Test
    void callNotifier() {
    when(webClient.post()).thenReturn(requestBodyUriSpec);
    when(notifierGatewayProperties.getUrl()).thenReturn("https://someurl/notify");
    when(requestBodySpec.bodyValue(any(Notifier.NotifierBody.class))).thenReturn(requestHeadersSpec);
    when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
    when(responseSpec.bodyToMono(Notifier.NotifierBody.class)).thenReturn(Mono.just(new Notifier.NotifierBody(
            "warning", "tes", "test message!")));
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

    EmployeeEntity employeeEntity = getEmployeeEntity1();

        Notifier.NotifierBody notifierBody = notifier.callNotifier(employeeEntity);
        assertThat(notifierBody.employeeAbbreviation()).isEqualTo("tes");
        assertThat(notifierBody.level()).isEqualTo("warning");
        assertThat(notifierBody.message()).isEqualTo("test message!");
    }

    private EmployeeEntity getEmployeeEntity1() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("TEST_1");
        employeeEntity.setLastName("TEST_1");
        employeeEntity.setAbbreviation("tes");

        return employeeEntity;
    }
}
