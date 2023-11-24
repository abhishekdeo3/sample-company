package com.samplecompany.administration.service;

import com.samplecompany.administration.PostgreSQLExtension;
import com.samplecompany.administration.dto.Employee;
import com.samplecompany.administration.dto.UpdateEmployeeComputer;
import com.samplecompany.administration.exception.NotFoundException;
import com.samplecompany.administration.model.ComputerEntity;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.ComputerRepository;
import com.samplecompany.administration.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@ExtendWith(PostgreSQLExtension.class)
public class EmployeeServiceTest {

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ComputerService computerService;

    @BeforeEach
    void cleanUp() {

        computerRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void getComputersByEmployeeId() {

        ComputerEntity computer1 = saveComputerEntity1();
        ComputerEntity computer2 = saveComputerEntity3();

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(computer2.getEmployeeEntity().getEmployeeId());

        UpdateEmployeeComputer updateEmployeeComputer = new UpdateEmployeeComputer(employeeEntity.get().getEmployeeId());

        computerService.updateEmployeeComputer(computer1.getComputerId(), updateEmployeeComputer);

        Employee employee = employeeService.getComputersByEmployeeId(employeeEntity.get().getEmployeeId());

        assertThat(employee.computers().computers()).hasSize(2);
    }

    @Test
    void getComputersByEmployeeId_EmployeeNotFound() {

        Exception exception = assertThrows(NotFoundException.class, () ->
                employeeService.getComputersByEmployeeId(11111111L));

        assertThat(exception.getMessage()).isEqualTo("Employee with ID 11111111 not found");
    }

    private ComputerEntity saveComputerEntity1() {

        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setComputerName("TEST_COMPUTER_1");
        computerEntity.setDescription("TEST DESCRIPTION 1");
        computerEntity.setMacAddress("TEST:MAC:ADDRESS::1");
        computerEntity.setIpAddress("111.222.333.444");

        return computerRepository.save(computerEntity);
    }

    private ComputerEntity saveComputerEntity3() {

        EmployeeEntity employeeEntity = getEmployeeEntity1();

        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setComputerName("TEST_COMPUTER_2");
        computerEntity.setDescription("TEST DESCRIPTION 2");
        computerEntity.setMacAddress("TEST:MAC:ADDRESS::2");
        computerEntity.setIpAddress("111.222.333.555");
        computerEntity.setEmployeeEntity(employeeEntity);

        return computerRepository.save(computerEntity);
    }

    private EmployeeEntity getEmployeeEntity1() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("TEST_1");
        employeeEntity.setLastName("TEST_1");
        employeeEntity.setAbbreviation("tes");

        return employeeRepository.save(employeeEntity);
    }
}
