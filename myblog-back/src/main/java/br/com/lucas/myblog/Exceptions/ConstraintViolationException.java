package br.com.lucas.myblog.Exceptions;

public class ConstraintViolationException extends RuntimeException{
    public ConstraintViolationException(String message){
        super(message);
    }
}
