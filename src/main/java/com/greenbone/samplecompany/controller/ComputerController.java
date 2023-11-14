package com.greenbone.samplecompany.controller;

import com.greenbone.samplecompany.dto.Computer;
import com.greenbone.samplecompany.dto.Computers;
import com.greenbone.samplecompany.dto.CrudComputerDto;
import com.greenbone.samplecompany.service.ComputerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PostMapping
    public Computer create(@RequestBody CrudComputerDto crudComputerDto) {

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
    public Computer update(@PathVariable Long computerId, @RequestBody CrudComputerDto crudComputerDto) {

        return computerService.updateComputer(computerId, crudComputerDto);
    }

    @DeleteMapping("/{computerId}")
    public void delete(@PathVariable Long computerId) {

        computerService.delete(computerId);
    }
}
