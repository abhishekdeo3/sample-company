package com.greenbone.samplecompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record Computer(

        @JsonProperty(value = "computer_id", required = true)
        @Schema(description = "ID of the ComputerEntity")
        Long computerId,

        @JsonProperty(value = "mac_address", required = true)
        @Schema(description = "MAC Address of the ComputerEntity")
        String macAddress,

        @JsonProperty(value = "computer_name", required = true)
        @Schema(description = "Name of the ComputerEntity")
        String computerName,

        @JsonProperty(value = "ip_address", required = true)
        @Schema(description = "IP Address of the ComputerEntity")
        String ipAddress,

        @JsonProperty(value = "employee_abbreviation")
        @Schema(description = "Abbreviated name of the employee. It's unique three letters from name of the employee")
        String employeeAbbreviation,

        @JsonProperty(value = "description")
        @Schema(description = "Any description related to computer or employee")
        String description) {
}
