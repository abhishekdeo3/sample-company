package com.samplecompany.administration.service;

import com.samplecompany.administration.AbstractIT;
import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.CrudComputerDto;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.ComputerRepository;
import com.samplecompany.administration.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ComputerServiceTest extends AbstractIT {

    @Autowired
    private ComputerService computerService;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void cleanUp() {
        computerRepository.deleteAll();
    }

    @Test
    public void createComputerTest() {

        employeeRepository.save(getEmployeeEntity());

        Computer computer = computerService.createComputer(getCrudComputerDto());

        assertThat(computer.computerId()).isEqualTo(1000L);
        assertThat(computer.computerName()).isEqualTo("TEST_COMPUTER");
        assertThat(computer.macAddress()).isEqualTo("TEST:MAC:ADDRESS");
        assertThat(computer.ipAddress()).isEqualTo("111.222.333.444");
        assertThat(computer.description()).isEqualTo("TEST DESCRIPTION");
        assertThat(computer.employeeAbbreviation()).isEqualTo("tes");
    }

    private CrudComputerDto getCrudComputerDto() {

        return new CrudComputerDto(
                "TEST:MAC:ADDRESS",
                "TEST_COMPUTER",
                "111.222.333.444",
                1111L,
                "TEST DESCRIPTION"
        );
    }

    private EmployeeEntity getEmployeeEntity() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(1111L);
        employeeEntity.setFirstName("TEST");
        employeeEntity.setLastName("TEST");
        employeeEntity.setAbbreviation("tes");

        return employeeEntity;
    }
}
