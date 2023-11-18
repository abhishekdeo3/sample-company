package com.samplecompany.administration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateEmployeeComputer(
        @JsonProperty("employee_id")
        @Schema(description = "ID of the Employee", example = "1123")
        Long employeeId) {
}
