package com.samplecompany.administration.controller;

import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.Computers;
import com.samplecompany.administration.dto.CrudComputerDto;
import com.samplecompany.administration.dto.UpdateEmployeeComputer;
import com.samplecompany.administration.exception.BadRequestException;
import com.samplecompany.administration.service.ComputerService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/computers")
@Validated
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PostMapping
    public Computer create(@Valid @RequestBody CrudComputerDto crudComputerDto) {

        if (crudComputerDto == null || crudComputerDto.computerName() == null || crudComputerDto.ipAddress() == null ||
                crudComputerDto.macAddress() == null) {
            throw new BadRequestException("Empty Request Sent");
        }

        return computerService.createComputer(crudComputerDto);
    }

    @GetMapping
    public Computers getAll() {

        return computerService.getAllComputers();
    }

    @GetMapping("/{computerId}")
    public Computer get(@PathVariable Long computerId) {

        return computerService.getComputerDetails(computerId);
    }

    @PutMapping("/{computerId}")
    public Computer update(@PathVariable Long computerId, @Valid @RequestBody CrudComputerDto crudComputerDto) {

        if (crudComputerDto == null || crudComputerDto.computerName() == null ||
                crudComputerDto.ipAddress() == null || crudComputerDto.macAddress() == null) {
            throw new BadRequestException("Empty Request Sent");
        }

        return computerService.updateComputer(computerId, crudComputerDto);
    }

    @DeleteMapping("/{computerId}")
    public void delete(@PathVariable Long computerId) {

        computerService.delete(computerId);
    }

    @PatchMapping("/{computerId}")
    public Computer updateEmployeeComputer(@PathVariable Long computerId,
                                           @Valid @RequestBody UpdateEmployeeComputer updateEmployeeComputer) {

        if (updateEmployeeComputer == null) {
            throw new BadRequestException("Empty Request Sent");
        }

        return computerService.updateEmployeeComputer(computerId, updateEmployeeComputer);
    }
}
