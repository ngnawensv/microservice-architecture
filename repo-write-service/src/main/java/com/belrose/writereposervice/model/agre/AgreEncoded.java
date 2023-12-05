package com.belrose.writereposervice.model.agre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "agre-encoded")
public class AgreEncoded implements Serializable {
    @Id
    private  String id;
    @JsonProperty("dataEncoded")
    private String dataEncoded;
    @JsonProperty("timestamp")
    private Long timestamp;

}
