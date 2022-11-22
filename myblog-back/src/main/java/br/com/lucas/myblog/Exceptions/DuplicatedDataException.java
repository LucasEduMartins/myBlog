package br.com.lucas.myblog.Exceptions;

public class DuplicatedDataException extends RuntimeException{

    public DuplicatedDataException(String message){
        super(message);
    }
}
