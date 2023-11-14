package com.greenbone.samplecompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record CrudComputerDto(
        @JsonProperty(value = "mac_address", required = true)
        @Schema(description = "MAC Address of the ComputerEntity")
        String macAddress,

        @JsonProperty(value = "computer_name", required = true)
        @Schema(description = "Name of the ComputerEntity")
        String computerName,

        @JsonProperty(value = "ip_address", required = true)
        @Schema(description = "IP Address of the ComputerEntity")
        String ipAddress,

        @JsonProperty(value = "employee_id")
        @Schema(description = "ID of the employee")
        Long employeeId,

        @JsonProperty(value = "description")
        @Schema(description = "Any description related to computer or employee")
        String description) {
}
