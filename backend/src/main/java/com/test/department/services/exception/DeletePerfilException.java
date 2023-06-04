package com.test.department.services.exception;

public class DeletePerfilException extends Exception {

    public DeletePerfilException(String message) {
        super(message);
    }

    public DeletePerfilException(String message, Throwable cause) {
        super(message, cause);
    }
}