package com.belrose.docservice.model.agre;

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
public class AgreEncoded implements Serializable {
    @JsonProperty("dataEncoded")
    private String dataEncoded;
    @JsonProperty("timestamp")
    private Long timestamp;

}
