package com.belrose.docservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.belrose.docservice.exception.ErrorType.*;

@Slf4j
@AllArgsConstructor
@Getter
public enum ErrorCode {
    REPO_WRITE_BAD_REQUEST(get(REPO_WRITE_ERROR_400)," RW-1001","repo.error.badRequest"),
    REPO_WRITE_INTERNAL_SERVER_ERROR(get(REPO_WRITE_ERROR_400)," RW-1002","repo.error.internalServerError");

    private final String type;
    private final String code;
    private final String msg;

    private static final Map<ErrorCode, ErrorDetails> ERROR_CODE_DETAILS_MAP = Arrays.stream(ErrorCode.values()).collect(Collectors.toMap(errorCode -> errorCode, errorCode ->ErrorDetails.builder().code(errorCode.code).type(errorCode.type).msg(errorCode.msg).build()));
    public static ErrorDetails getDetails(ErrorCode errorCode){
        return ERROR_CODE_DETAILS_MAP.get(errorCode);
    }
}
