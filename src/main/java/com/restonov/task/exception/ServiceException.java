package com.restonov.task.exception;

public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = 2640756095807742655L;

  public ServiceException() {
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }
}
