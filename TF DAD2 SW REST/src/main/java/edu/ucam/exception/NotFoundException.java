package edu.ucam.exception;

public class NotFoundException extends ApiException{
	

	public NotFoundException(String message) {
		super(message);
		setHttpCode(404);
	}



}
