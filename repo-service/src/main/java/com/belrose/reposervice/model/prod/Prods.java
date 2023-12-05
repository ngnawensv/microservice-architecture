package com.belrose.reposervice.model.prod;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "prods")
public class Prods implements Serializable {
    @JsonProperty("catId")
    private String catId;
    @JsonProperty("prods")
    private List<Prod> prods;

}
