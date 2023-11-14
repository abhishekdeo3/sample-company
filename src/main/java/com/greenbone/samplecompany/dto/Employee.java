package com.greenbone.samplecompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record Employee(

        String firstName,
        String lastName,
        String abbreviation,
        List<Computer> computerList) {
}
