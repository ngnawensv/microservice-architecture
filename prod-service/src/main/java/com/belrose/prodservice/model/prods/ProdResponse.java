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
public class ProdResponse implements Serializable {
    @JsonProperty("categoryId")
    private String categoryId;
    @JsonProperty("product")
    private Prod product;
}
