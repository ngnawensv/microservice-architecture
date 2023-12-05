package com.belrose.reposervice.model.prod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prod implements Serializable {
    @JsonProperty("prodId")
    private String prodId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
}
