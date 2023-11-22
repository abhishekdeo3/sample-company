package com.samplecompany.administration.repository;

import com.samplecompany.administration.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = " SELECT employee.* FROM sample_company.employee " +
            " JOIN sample_company.computer ON employee.id = computer.employee_id " +
            " GROUP BY employee.id HAVING COUNT(computer.id) >= :max_computer_count", nativeQuery = true)
    List<EmployeeEntity> getAllEmployeesWithMoreThanThreeComputers(@Param("max_computer_count") Integer maxComputerCount);

    @Query(value = " SELECT employee.* FROM sample_company.employee " +
            " JOIN sample_company.computer ON employee.id = computer.employee_id " +
            " WHERE employee.id = :employee_id " +
            " GROUP BY employee.id HAVING COUNT(computer.id) >= :max_computer_count", nativeQuery = true)
    Optional<EmployeeEntity> getEmployeesWithMoreThanThreeComputers(@Param("employee_id") Long employee_id,
                                                                   @Param("max_computer_count") Integer maxComputerCount);
}
