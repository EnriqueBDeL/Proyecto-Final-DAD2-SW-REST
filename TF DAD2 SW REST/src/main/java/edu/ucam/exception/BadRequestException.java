package edu.ucam.exception;

public class BadRequestException extends ApiException {
  
	public BadRequestException(String message) {
        super(message);
        setHttpCode(400);
    }
	
}