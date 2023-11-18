package com.samplecompany.administration.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Returns exception response in readable format")
public class Problem {

    @JsonProperty(value = "status")
    @Schema(description = "HTTP Status Code", example = "401")
    private @NotNull int status;

    @JsonProperty(value = "title")
    @Schema(description = "Title of Response", example = "UNAUTHORIZED")
    private String title;

    @JsonProperty(value = "details")
    @Schema(description = "Details of the Error", example = "Invalid Input Provided")
    private String detail;

    public Problem(int status, String title, String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
    }
}
