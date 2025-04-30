package com.wiseai.reservemeetingroom.core.exception;

public abstract class ServiceUnavailableException extends RuntimeException {
  public ServiceUnavailableException(String message) {
    super(message);
  }

  public abstract String getErrorCode();
}
