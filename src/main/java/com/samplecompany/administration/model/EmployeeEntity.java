package com.samplecompany.administration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ABBREVIATION")
    private String abbreviation;

    @OneToMany(mappedBy = "employeeEntity", fetch = FetchType.EAGER)
    private Set<ComputerEntity> computerEntitySet;
}
