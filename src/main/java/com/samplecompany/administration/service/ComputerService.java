package com.samplecompany.administration.service;

import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.Computers;
import com.samplecompany.administration.dto.CrudComputerDto;
import com.samplecompany.administration.dto.UpdateEmployeeComputer;
import com.samplecompany.administration.exception.NotFoundException;
import com.samplecompany.administration.model.ComputerEntity;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.ComputerRepository;
import com.samplecompany.administration.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    private final EmployeeRepository employeeRepository;

    public ComputerService(ComputerRepository computerRepository, EmployeeRepository employeeRepository) {
        this.computerRepository = computerRepository;
        this.employeeRepository = employeeRepository;
    }

    public Computer createComputer(CrudComputerDto crudComputerDto) {

        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setComputerName(crudComputerDto.computerName());
        computerEntity.setMacAddress(crudComputerDto.macAddress());
        computerEntity.setIpAddress(crudComputerDto.ipAddress());
        computerEntity.setDescription(crudComputerDto.description());

        if (crudComputerDto.employeeId() != null) {

            EmployeeEntity employeeEntity = employeeRepository.findById(crudComputerDto.employeeId())
                    .orElseThrow(() -> new NotFoundException(String.format("Employee with ID %s not found", crudComputerDto.employeeId())));

            computerEntity.setEmployeeEntity(employeeEntity);
        }

        ComputerEntity saved = computerRepository.save(computerEntity);

        return new Computer(saved.getComputerId(), saved.getMacAddress(), saved.getComputerName(), saved.getIpAddress(),
                saved.getEmployeeEntity() != null ? saved.getEmployeeEntity().getAbbreviation() : null, saved.getDescription());
    }

    public Computer getComputerDetails(Long computerId) {

        Optional<ComputerEntity> computerEntity = computerRepository.findById(computerId);

        return computerEntity.map(entity -> new Computer(entity.getComputerId(), entity.getMacAddress(),
                entity.getComputerName(), entity.getIpAddress(),
                entity.getEmployeeEntity() != null ? entity.getEmployeeEntity().getAbbreviation() : null,
                entity.getDescription())).orElseThrow(() -> new NotFoundException(String.format("Computer with ID %s not found", computerId)));
    }

    public Computers getAllComputers() {

        List<ComputerEntity> computerEntities = computerRepository.findAll();

        List<Computer> computerList = computerEntities.stream().map(computerEntity -> new Computer(computerEntity.getComputerId(),
                computerEntity.getMacAddress(), computerEntity.getComputerName(), computerEntity.getIpAddress(),
                computerEntity.getEmployeeEntity() != null ? computerEntity.getEmployeeEntity().getAbbreviation() : null,
                computerEntity.getDescription())).toList();

        return new Computers(computerList);
    }

    public Computer updateComputer(Long computerId, CrudComputerDto crudComputerDto) {

        Optional<ComputerEntity> computerEntity = computerRepository.findById(computerId);

        if (computerEntity.isEmpty()) {
            throw new NotFoundException(String.format("Computer with ID %s not found", computerId));
        } else {
            computerEntity.get().setComputerName(crudComputerDto.computerName());
            computerEntity.get().setMacAddress(crudComputerDto.macAddress());
            computerEntity.get().setIpAddress(crudComputerDto.ipAddress());
            computerEntity.get().setDescription(crudComputerDto.description());

            if (crudComputerDto.employeeId() != null) {
                Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(crudComputerDto.employeeId());

                if (employeeEntity.isPresent()) {
                    computerEntity.get().setEmployeeEntity(employeeEntity.get());
                } else {
                    throw new NotFoundException(String.format("Employee with ID %s not found", crudComputerDto.employeeId()));
                }
            }

            ComputerEntity saved = computerRepository.save(computerEntity.get());

            return new Computer(saved.getComputerId(), saved.getMacAddress(), saved.getComputerName(), saved.getIpAddress(),
                    saved.getEmployeeEntity() != null ? saved.getEmployeeEntity().getAbbreviation() : null, saved.getDescription());
        }
    }

    public void delete(Long computerId) {

        Optional<ComputerEntity> computerEntity = computerRepository.findById(computerId);

        if (computerEntity.isEmpty()) {
            throw new NotFoundException(String.format("Computer with ID %s not found", computerId));
        } else {
            computerRepository.deleteById(computerEntity.get().getComputerId());
        }
    }

    public Computer updateEmployeeComputer(Long computerId, UpdateEmployeeComputer updateEmployeeComputer) {

        Optional<ComputerEntity> computerEntity = computerRepository.findById(computerId);

        if (computerEntity.isEmpty()) {
            throw new NotFoundException(String.format("Computer with ID %s not found", computerId));
        } else {

            if (updateEmployeeComputer.employeeId() != null) {

                Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(updateEmployeeComputer.employeeId());

                if (employeeEntity.isPresent()) {
                    computerEntity.get().setEmployeeEntity(employeeEntity.get());
                } else {
                    throw new NotFoundException(String.format("Employee with ID %s not found", updateEmployeeComputer.employeeId()));
                }
            } else {
                computerEntity.get().setEmployeeEntity(null);
            }

            ComputerEntity saved = computerRepository.save(computerEntity.get());

            return new Computer(saved.getComputerId(), saved.getMacAddress(), saved.getComputerName(), saved.getIpAddress(),
                    saved.getEmployeeEntity() != null ? saved.getEmployeeEntity().getAbbreviation() : null, saved.getDescription());
        }
    }
}
