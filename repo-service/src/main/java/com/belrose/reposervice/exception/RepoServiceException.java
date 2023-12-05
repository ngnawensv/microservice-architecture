package com.belrose.reposervice.exception;

public class RepoServiceException extends RuntimeException{
  public RepoServiceException(String message) {
    super(message);
  }
  public RepoServiceException(String message, Throwable throwable) {
    super(message,throwable);
  }
  public RepoServiceException(Throwable throwable) {
    super(throwable);
  }
}
