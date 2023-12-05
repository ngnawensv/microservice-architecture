package com.belrose.docservice.model.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaDataFile  implements Serializable {
    @JsonProperty("originalFileName")
    private String  originalFileName;
    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("createdBy")
    private String  createdBy;
    @JsonProperty("createdDate")
    private String createdDate;
}
