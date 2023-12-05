package com.belrose.docservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public enum ErrorType {
    REPO_WRITE_ERROR_500("TechnicalRepoWriteError500"),
    REPO_WRITE_ERROR_400("TechnicalRepoWriteError400");

    private final String name;

    private static final Map<ErrorType, String> ERROR_TYPE_STRING_MAP = Arrays.stream(ErrorType.values()).collect(Collectors.toMap(errorType -> errorType,errorType -> errorType.name));
    public static String get(ErrorType errorType){
        return ERROR_TYPE_STRING_MAP.get(errorType);
    }

}
