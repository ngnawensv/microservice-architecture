package com.belrose.microsvclistener.model.file;

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
    private String  originalFileName;
    private String fileName;
    private String displayName;
    private String  createdBy;
    private String createdDate;
}
