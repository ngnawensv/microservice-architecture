package com.belrose.prodservice.model.prods;

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
class Prod implements Serializable {
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
}
