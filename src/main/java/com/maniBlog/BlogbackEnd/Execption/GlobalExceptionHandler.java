package com.maniBlog.BlogbackEnd.Execption;

import com.maniBlog.BlogbackEnd.PayLoad.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    //handle specific exception
   @ExceptionHandler(ResourceNotFoundExecption.class)
    public ResponseEntity<ErrorDetails> ResourceNotFoundHandler(
            ResourceNotFoundExecption execption, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), execption.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiExecption.class)
    public ResponseEntity<ErrorDetails> BlogApiExecptionHandler(
            BlogApiExecption execption, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), execption.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> ExecptionHandler(
            BlogApiExecption execption, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), execption.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
