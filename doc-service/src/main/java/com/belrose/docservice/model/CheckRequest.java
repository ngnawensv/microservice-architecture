package com.belrose.docservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckRequest  implements Serializable {
    @NotEmpty(message = "checkingElement can not be null")
    @JsonProperty("checkingElement")
    private String checkingElement;
}
