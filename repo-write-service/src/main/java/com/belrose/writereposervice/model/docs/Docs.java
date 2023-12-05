package com.belrose.writereposervice.model.docs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "docs")
public class Docs implements Serializable {
    @Id
 private String id;
 private String name;
 private String creationDate;
}
