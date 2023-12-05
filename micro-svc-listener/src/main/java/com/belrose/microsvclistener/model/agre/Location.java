package com.belrose.microsvclistener.model.agre;

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
public class Location implements Serializable {
    @JsonProperty("road")
    private String road;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private  String country;
}
