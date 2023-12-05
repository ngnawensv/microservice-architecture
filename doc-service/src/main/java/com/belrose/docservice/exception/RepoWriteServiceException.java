package com.belrose.docservice.exception;

public class RepoWriteServiceException extends RuntimeException{
    private ErrorDetails errorDetails;

    private String httpStatus;

    public RepoWriteServiceException(String httpStatus,ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
        this.httpStatus = httpStatus;
    }

    public RepoWriteServiceException(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public RepoWriteServiceException(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}
