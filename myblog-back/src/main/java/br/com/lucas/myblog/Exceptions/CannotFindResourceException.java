package br.com.lucas.myblog.Exceptions;

public class CannotFindResourceException extends RuntimeException{
    public CannotFindResourceException(String message){
        super(message);
    }
}
