package com.belrose.docservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails implements Serializable {
    private final String type;
    private  final String code;
    private final String msg;
    private List<String> additionalInfos;
}
