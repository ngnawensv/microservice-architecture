package com.belrose.writereposervice.model.agre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActionDetails implements Serializable {
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("agreTransaction")
    private  String agreTransaction;
    @JsonProperty("timestamp")
    private Long timestamp;
}
