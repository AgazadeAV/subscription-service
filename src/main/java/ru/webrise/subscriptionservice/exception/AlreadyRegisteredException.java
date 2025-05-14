package ru.webrise.subscriptionservice.exception;

public class AlreadyRegisteredException extends RuntimeException {
  public AlreadyRegisteredException(String message) {
    super(message);
  }
}
