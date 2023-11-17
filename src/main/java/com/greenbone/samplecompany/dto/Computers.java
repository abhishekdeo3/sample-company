package com.greenbone.samplecompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Computers object with List of all Computers")
public record Computers(
        @JsonProperty("computers")
        @Schema(description = "List of all Computers")
        List<Computer> computers
) {
}
