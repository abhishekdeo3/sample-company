package com.samplecompany.administration.service;

import com.samplecompany.administration.PostgreSQLExtension;
import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.Computers;
import com.samplecompany.administration.dto.CrudComputerDto;
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
class ComputerServiceTest {
    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ComputerService computerService;

    @BeforeEach
    void cleanUp() {

        computerRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void createComputerTest() {

        Computer computer = computerService.createComputer(getCrudComputerDto1());

        assertThat(computer.computerName()).isEqualTo("TEST_COMPUTER");
        assertThat(computer.macAddress()).isEqualTo("TEST:MAC:ADDRESS");
        assertThat(computer.ipAddress()).isEqualTo("111.222.333.444");
        assertThat(computer.description()).isEqualTo("TEST DESCRIPTION");
        assertThat(computer.employeeAbbreviation()).isEqualTo("tes");
    }

    @Test
    void createComputerTest_employeeNotFound() {

        Exception exception = assertThrows(NotFoundException.class, () ->
                computerService.createComputer(getCrudComputerDtoNoEmployee()));

        assertThat(exception.getMessage()).isEqualTo("Employee with ID 111111 not found");
    }

    @Test
    void getComputerDetails() {

        ComputerEntity computerEntity = saveComputerEntity1();
        Computer computer = computerService.getComputerDetails(computerEntity.getComputerId());

        assertThat(computer.computerId()).isEqualTo(computerEntity.getComputerId());
        assertThat(computer.computerName()).isEqualTo("TEST_COMPUTER_1");
        assertThat(computer.macAddress()).isEqualTo("TEST:MAC:ADDRESS::1");
        assertThat(computer.ipAddress()).isEqualTo("111.222.333.444");
        assertThat(computer.description()).isEqualTo("TEST DESCRIPTION 1");
    }

    @Test
    void getAllComputers() {

        ComputerEntity computer1 = saveComputerEntity1();
        ComputerEntity computer2 = saveComputerEntity2();

        Computers computers = computerService.getAllComputers();

        assertThat(computers.computers()).hasSize(2);

        assertThat(computers.computers().get(0).computerId()).isEqualTo(computer1.getComputerId());
        assertThat(computers.computers().get(0).computerName()).isEqualTo(computer1.getComputerName());
        assertThat(computers.computers().get(0).description()).isEqualTo(computer1.getDescription());
        assertThat(computers.computers().get(0).ipAddress()).isEqualTo(computer1.getIpAddress());
        assertThat(computers.computers().get(0).macAddress()).isEqualTo(computer1.getMacAddress());

        assertThat(computers.computers().get(1).computerId()).isEqualTo(computer2.getComputerId());
        assertThat(computers.computers().get(1).computerName()).isEqualTo(computer2.getComputerName());
        assertThat(computers.computers().get(1).description()).isEqualTo(computer2.getDescription());
        assertThat(computers.computers().get(1).ipAddress()).isEqualTo(computer2.getIpAddress());
        assertThat(computers.computers().get(1).macAddress()).isEqualTo(computer2.getMacAddress());
    }

    @Test
    void getComputerTest_computerNotFound() {

        Exception exception = assertThrows(NotFoundException.class, () ->
                computerService.getComputerDetails(2000000002L));

        assertThat(exception.getMessage()).isEqualTo("Computer with ID 2000000002 not found");
    }

    @Test
    void updateComputerTest() {

        ComputerEntity computer = saveComputerEntity1();

        CrudComputerDto crudComputerDto = new CrudComputerDto("TEST:MAC:ADDRESS:TEST",
                "TEST_COMPUTER_1", "111.222.333.555", null,
                "TEST DESCRIPTION UPDATED");

        Computer updated = computerService.updateComputer(computer.getComputerId(), crudComputerDto);

        assertThat(updated.computerId()).isEqualTo(computer.getComputerId());
        assertThat(updated.computerName()).isEqualTo("TEST_COMPUTER_1");
        assertThat(updated.employeeAbbreviation()).isNull();
        assertThat(updated.macAddress()).isEqualTo("TEST:MAC:ADDRESS:TEST");
        assertThat(updated.ipAddress()).isEqualTo("111.222.333.555");
        assertThat(updated.description()).isEqualTo("TEST DESCRIPTION UPDATED");
    }

    @Test
    void updateComputerTest_employeeNotFound() {

        ComputerEntity computer = saveComputerEntity1();

        CrudComputerDto crudComputerDto = new CrudComputerDto("TEST:MAC:ADDRESS:TEST",
                "TEST_COMPUTER_1", "111.222.333.555", 111111L,
                "TEST DESCRIPTION UPDATED");

        Exception exception = assertThrows(NotFoundException.class, () ->
                computerService.updateComputer(computer.getComputerId(), crudComputerDto));

        assertThat(exception.getMessage()).isEqualTo("Employee with ID 111111 not found");
    }

    @Test
    void updateComputerTest_computerNotFound() {

        Exception exception = assertThrows(NotFoundException.class, () ->
                computerService.updateComputer(111111L, getCrudComputerDtoNoEmployee()));

        assertThat(exception.getMessage()).isEqualTo("Computer with ID 111111 not found");
    }

    @Test
    void deleteComputerTest() {

        ComputerEntity computer = saveComputerEntity1();

        computerService.delete(computer.getComputerId());

        Exception exception = assertThrows(NotFoundException.class, () ->
                computerService.getComputerDetails(computer.getComputerId()));

        assertThat(exception.getMessage()).isEqualTo("Computer with ID " + computer.getComputerId() +" not found");
    }

    @Test
    void deleteComputerTest_NOT_FOUND() {

        Exception exception = assertThrows(NotFoundException.class, () ->
                computerService.delete(2222222222L));

        assertThat(exception.getMessage()).isEqualTo("Computer with ID 2222222222 not found");
    }

    @Test
    void updateEmployeeComputerTest() {

        ComputerEntity computer1 = saveComputerEntity1();
        ComputerEntity computer2 = saveComputerEntity3();

        Optional<EmployeeEntity> employeeEntity  = employeeRepository.findById(computer2.getEmployeeEntity().getEmployeeId());

        assertThat(computer1.getEmployeeEntity()).isNull();

        UpdateEmployeeComputer updateEmployeeComputer = new UpdateEmployeeComputer(computer2.getEmployeeEntity().getEmployeeId());

        Computer updated = computerService.updateEmployeeComputer(computer1.getComputerId(), updateEmployeeComputer);

        assertThat(updated.computerId()).isEqualTo(computer1.getComputerId());
        assertThat(updated.employeeAbbreviation()).isEqualTo(employeeEntity.get().getAbbreviation());

        Optional<ComputerEntity> computerEntity1 = computerRepository.findById(computer1.getComputerId());
        assertThat(computerEntity1.get().getEmployeeEntity().getEmployeeId()).isEqualTo(employeeEntity.get().getEmployeeId());
    }


    @Test
    void updateEmployeeComputerTest_removeFromEmployee() {

        ComputerEntity computer1 = saveComputerEntity1();
        ComputerEntity computer2 = saveComputerEntity3();

        Optional<EmployeeEntity> employeeEntity  = employeeRepository.findById(computer2.getEmployeeEntity().getEmployeeId());

        assertThat(computer1.getEmployeeEntity()).isNull();

        UpdateEmployeeComputer updateEmployeeComputer = new UpdateEmployeeComputer(computer2.getEmployeeEntity().getEmployeeId());

        Computer updated = computerService.updateEmployeeComputer(computer1.getComputerId(), updateEmployeeComputer);

        assertThat(updated.computerId()).isEqualTo(computer1.getComputerId());
        assertThat(updated.employeeAbbreviation()).isEqualTo(employeeEntity.get().getAbbreviation());

        Optional<ComputerEntity> computerEntity1 = computerRepository.findById(computer1.getComputerId());
        assertThat(computerEntity1.get().getEmployeeEntity().getEmployeeId()).isEqualTo(employeeEntity.get().getEmployeeId());

    }



    private ComputerEntity saveComputerEntity1() {

        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setComputerName("TEST_COMPUTER_1");
        computerEntity.setDescription("TEST DESCRIPTION 1");
        computerEntity.setMacAddress("TEST:MAC:ADDRESS::1");
        computerEntity.setIpAddress("111.222.333.444");

        return computerRepository.save(computerEntity);
    }

    private ComputerEntity saveComputerEntity2() {

        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setComputerName("TEST_COMPUTER_2");
        computerEntity.setDescription("TEST DESCRIPTION 2");
        computerEntity.setMacAddress("TEST:MAC:ADDRESS::2");
        computerEntity.setIpAddress("111.222.333.555");

        return computerRepository.save(computerEntity);
    }

    private CrudComputerDto getCrudComputerDto1() {

        return new CrudComputerDto(
                "TEST:MAC:ADDRESS",
                "TEST_COMPUTER",
                "111.222.333.444",
                getEmployeeEntity1().getEmployeeId(),
                "TEST DESCRIPTION"
        );
    }

    private EmployeeEntity getEmployeeEntity1() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("TEST_1");
        employeeEntity.setLastName("TEST_1");
        employeeEntity.setAbbreviation("tes");

        return employeeRepository.save(employeeEntity);
    }

    private EmployeeEntity getEmployeeEntity2() {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("TEST_2");
        employeeEntity.setLastName("TEST_2");
        employeeEntity.setAbbreviation("est");

        return employeeRepository.save(employeeEntity);
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

    private CrudComputerDto getCrudComputerDtoNoEmployee() {

        return new CrudComputerDto(
                "TEST:MAC:ADDRESS",
                "TEST_COMPUTER",
                "111.222.333.444",
                111111L,
                "TEST DESCRIPTION"
        );
    }
}
