package com.belrose.microsvclistener.model.agre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "agre")
public class Agre implements Serializable {

    @JsonProperty("agreId")
    @Id
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
