package com.samplecompany.administration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Information of the Computer")
public record Computer(

        @JsonProperty(value = "computer_id", required = true)
        @Schema(description = "ID of the ComputerEntity", example = "123456")
        Long computerId,

        @JsonProperty(value = "mac_address", required = true)
        @Schema(description = "MAC Address of the ComputerEntity", example = "AA:5645486:BGGHHH:4564")
        String macAddress,

        @JsonProperty(value = "computer_name", required = true)
        @Schema(description = "Name of the ComputerEntity", example = "TEST2000_HR")
        String computerName,

        @JsonProperty(value = "ip_address", required = true)
        @Schema(description = "IP Address of the ComputerEntity", example = "175.56.89.90")
        String ipAddress,

        @JsonProperty(value = "employee_abbreviation")
        @Schema(description = "Abbreviated name of the employee. It's unique three letters from name of the employee",
                example = "mmu")
        String employeeAbbreviation,

        @JsonProperty(value = "description")
        @Schema(description = "Any description related to computer or employee", example = "TEST Machine")
        String description) {
}
