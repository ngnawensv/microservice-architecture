package com.belrose.agreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"agreId","name","type","version","actionDetails","locationList"})
public class Agre implements Serializable {
    @JsonProperty("agreId")
    private String agreId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("version")
    private String version;
    @JsonProperty("name")
    private  String name;
    @JsonProperty("addressList")
    private List<Address> addressList;
    @JsonProperty("locationList")
    private List<Location> locationList;
    @JsonProperty("actionDetails")
    private ActionDetails actionDetails;

}
