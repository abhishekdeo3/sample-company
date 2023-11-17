package com.greenbone.samplecompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Object for CRUD Operations of Computer")
public record CrudComputerDto(
        @JsonProperty(value = "mac_address", required = true)
        @Schema(description = "MAC Address of the ComputerEntity", example = "AA:5645486:BGGHHH:4564")
        String macAddress,

        @JsonProperty(value = "computer_name", required = true)
        @Schema(description = "Name of the ComputerEntity", example = "TEST2000_HR")
        String computerName,

        @JsonProperty(value = "ip_address", required = true)
        @Schema(description = "IP Address of the ComputerEntity", example = "175.56.89.90")
        String ipAddress,

        @JsonProperty(value = "employee_id")
        @Schema(description = "ID of the employee", example = "1123")
        Long employeeId,

        @JsonProperty(value = "description")
        @Schema(description = "Any description related to computer or employee", example = "TEST Machine")
        String description) {
}
