package br.com.lucas.myblog.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private Date timeStamp;
    private String message;
    private String details;
    private List<Field> fields;

    @Getter
    @AllArgsConstructor
    public static class Field {
        private String name;
        private String message;
    }
}
