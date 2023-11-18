package com.samplecompany.administration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Employee information")
public record Employee(

        @JsonProperty(value = "first_name")
        @Schema(description = "First name of the employee", example = "John")
        String firstName,

        @JsonProperty(value = "last_name")
        @Schema(description = "Last name of the employee", example = "Smith")
        String lastName,

        @JsonProperty(value = "abbreviation")
        @Schema(description = "Unique abbreviation of the employee name", example = "jsm")
        String abbreviation,

        @JsonProperty(value = "computers")
        @Schema(description = "List of the Computers assigned to an employee")
        Computers computers) {
}
