package br.com.lucas.myblog.Exceptions;

public class InvalidReferenceIdException extends RuntimeException{
    public InvalidReferenceIdException(String message){
        super(message);
    }
}
