package com.maniBlog.BlogbackEnd.Execption;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BlogApiExecption extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogApiExecption(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogApiExecption(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
