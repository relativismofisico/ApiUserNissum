package org.nisum.nisumapi.exceptions;

public class InternalServerErrorException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
