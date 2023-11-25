package com.samplecompany.administration.controller;

import com.samplecompany.administration.PostgreSQLExtension;
import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.Computers;
import com.samplecompany.administration.dto.Employee;
import com.samplecompany.administration.exception.NotFoundException;
import com.samplecompany.administration.service.EmployeeService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ExtendWith(PostgreSQLExtension.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getComputersByEmployee() throws Exception {

        Computers computers = new Computers(List.of(new Computer(1L, "123::test::123",
                "TEST_COMP", "12345", null, "TEST DES")));

        Employee employee = new Employee("test", "test", "tes", computers);

        when(employeeService.getComputersByEmployeeId(any())).thenReturn(employee);

        mockMvc.perform(get("/employees/{employeeId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"first_name\":\"test\",\"last_name\":\"test\"," +
                        "\"abbreviation\":\"tes\",\"computers\":{\"computers\":[{\"computer_id\":1," +
                        "\"mac_address\":\"123::test::123\",\"computer_name\":\"TEST_COMP\",\"ip_address\":\"12345\"," +
                        "\"employee_abbreviation\":null,\"description\":\"TEST DES\"}]}}"));
    }

    @Test
    void getComputersByEmployee_employeeNotFound() throws Exception {

        when(employeeService.getComputersByEmployeeId(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/employees/{employeeId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"status\":404,\"title\":\"Not Found\",\"details\":null}"));
    }
}
