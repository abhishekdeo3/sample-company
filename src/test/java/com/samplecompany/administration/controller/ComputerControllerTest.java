package com.samplecompany.administration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samplecompany.administration.PostgreSQLExtension;
import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.Computers;
import com.samplecompany.administration.dto.CrudComputerDto;
import com.samplecompany.administration.dto.UpdateEmployeeComputer;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.ComputerRepository;
import com.samplecompany.administration.service.ComputerService;
import com.samplecompany.administration.service.Notifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ExtendWith(PostgreSQLExtension.class)
public class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComputerService computerService;

    @MockBean
    private ComputerRepository computerRepository;

    @MockBean
    private Notifier notifier;

    @BeforeEach
    void cleanUp() {
        doNothing().when(notifier).init();
        computerRepository.deleteAll();
    }

    @Test
    void create_throwsBadRequest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        CrudComputerDto crudComputerDto = new CrudComputerDto(null, null, null,
                null, null);

        mockMvc.perform(post("/computers")
                        .content(objectMapper.writeValueAsBytes(crudComputerDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"status\":400,\"title\":\"Bad Request\",\"details\":\"Empty Request Sent\"}"));
    }

    @Test
    void create() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        CrudComputerDto crudComputerDto = new CrudComputerDto("123::test::123", "TEST_COMP",
                "12345", null, "TEST DES");

        when(computerService.createComputer(crudComputerDto)).thenReturn(new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", null, "TEST DES"));

        mockMvc.perform(post("/computers")
                        .content(objectMapper.writeValueAsBytes(crudComputerDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"computer_id\":1,\"mac_address\":\"123::test::123\"," +
                        "\"computer_name\":\"TEST_COMP\",\"ip_address\":\"12345\",\"employee_abbreviation\":null," +
                        "\"description\":\"TEST DES\"}"));
    }

    @Test
    void getAll() throws Exception {

        Computers computers = new Computers(List.of(new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", null, "TEST DES")));

        when(computerService.getAllComputers()).thenReturn(computers);

        mockMvc.perform(get("/computers"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"computers\":[{\"computer_id\":1," +
                        "\"mac_address\":\"123::test::123\",\"computer_name\":\"TEST_COMP\",\"ip_address\":\"12345\"," +
                        "\"employee_abbreviation\":null,\"description\":\"TEST DES\"}]}"));
    }

    @Test
    void getComputerDetails() throws Exception {

        Computer computer = new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", null, "TEST DES");

        when(computerService.getComputerDetails(1L)).thenReturn(computer);

        mockMvc.perform(get("/computers/{computerId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"computer_id\":1,\"mac_address\":\"123::test::123\"," +
                        "\"computer_name\":\"TEST_COMP\",\"ip_address\":\"12345\",\"employee_abbreviation\":null," +
                        "\"description\":\"TEST DES\"}"));
    }

    @Test
    void update() throws Exception {

        Computer computer = new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", null, "TEST DES");

        CrudComputerDto crudComputerDto = new CrudComputerDto("456::TEST::456", "UPDATED_COMP",
                "789456", null, "UPDATED DES");

        Computer updated = new Computer(1L, "456::TEST::456", "UPDATED_COMP",
                "789456", null, "UPDATED DES");

        when(computerService.getComputerDetails(1L)).thenReturn(computer);
        when(computerService.updateComputer(1L, crudComputerDto)).thenReturn(updated);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/computers/{computerId}", 1L)
                        .content(objectMapper.writeValueAsBytes(crudComputerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"computer_id\":1,\"mac_address\":\"456::TEST::456\"," +
                        "\"computer_name\":\"UPDATED_COMP\",\"ip_address\":\"789456\",\"employee_abbreviation\":null," +
                        "\"description\":\"UPDATED DES\"}"));
    }

    @Test
    void deleteComputer() throws Exception {

        Computer computer = new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", null, "TEST DES");

        when(computerService.getComputerDetails(1L)).thenReturn(computer);

        mockMvc.perform(delete("/computers/{computerId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeComputer() throws Exception {

        Computer computer = new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", "tes", "TEST DES");

        UpdateEmployeeComputer updateEmployeeComputer = new UpdateEmployeeComputer(1L);

        when(computerService.updateEmployeeComputer(1L, updateEmployeeComputer))
                .thenReturn(computer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(patch("/computers/{computerId}", 1)
                        .content(objectMapper.writeValueAsBytes(updateEmployeeComputer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("{\"computer_id\":1," +
                        "\"mac_address\":\"123::test::123\",\"computer_name\":\"TEST_COMP\",\"ip_address\":\"12345\"," +
                        "\"employee_abbreviation\":\"tes\",\"description\":\"TEST DES\"}"));
    }
}
