package com.samplecompany.administration.controller;

import com.samplecompany.administration.dto.Employee;
import com.samplecompany.administration.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public Employee getComputersByEmployee(@PathVariable Long employeeId) {

        return employeeService.getComputersByEmployeeId(employeeId);
    }
}
