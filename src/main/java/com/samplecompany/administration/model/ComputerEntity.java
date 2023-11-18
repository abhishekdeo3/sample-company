package com.samplecompany.administration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COMPUTER")
@Getter
@Setter
public class ComputerEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long computerId;

    @Column(name = "MAC_ADDRESS")
    private String macAddress;

    @Column(name = "COMPUTER_NAME")
    private String computerName;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    private EmployeeEntity employeeEntity;

}
