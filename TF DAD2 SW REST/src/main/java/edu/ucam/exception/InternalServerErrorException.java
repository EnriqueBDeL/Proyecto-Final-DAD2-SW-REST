package edu.ucam.exception;

public class InternalServerErrorException extends ApiException {
    
	public InternalServerErrorException(String message) {
        super(message);
        setHttpCode(500);
    }
	
}