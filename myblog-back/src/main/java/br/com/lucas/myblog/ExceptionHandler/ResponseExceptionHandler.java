package br.com.lucas.myblog.ExceptionHandler;

import br.com.lucas.myblog.Exceptions.CannotFindResourceException;
import br.com.lucas.myblog.Exceptions.ConstraintViolationException;
import br.com.lucas.myblog.Exceptions.DuplicatedDataException;
import br.com.lucas.myblog.Models.ExceptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception error, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), error.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CannotFindResourceException.class)
    public final ResponseEntity<ExceptionResponse> handleCannotFindResourceException(Exception error, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), error.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public final ResponseEntity<ExceptionResponse> handleDuplicatedDataException(Exception error, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), error.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ExceptionResponse.Field> fields = new ArrayList<>();

        for(ObjectError error: ex.getBindingResult().getAllErrors()){
            fields.add(new ExceptionResponse.Field(((FieldError) error).getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        }

        String message = "One or more fields is invalid, fill in correctly.";
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message, request.getDescription(false), fields);

        return this.handleExceptionInternal(ex, exceptionResponse, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleConstraintViolationException(Exception error, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), error.getMessage(), request.getDescription(false), null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
