package com.belrose.reposervice.model.agre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "agre")
public class Agre {
    private String agreId;
    private String type;
    private String version;
    private  String name;

}
