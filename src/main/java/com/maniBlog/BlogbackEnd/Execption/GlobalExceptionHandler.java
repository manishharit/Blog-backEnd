package com.maniBlog.BlogbackEnd.Execption;

import com.maniBlog.BlogbackEnd.PayLoad.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

       Map<String,String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach(
                  (error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });

          return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
   }

   // Or rather than extending and over riding Custom can be used like

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> MethodArgumentNotValidExceptionHandler(
//            MethodArgumentNotValidException exception,
//            WebRequest webRequest){
//        Map<String,String> errors = new HashMap<>();
//        exception.getBindingResult().getAllErrors().forEach(
//                (error)->{
//                    String fieldName = ((FieldError)error).getField();
//                    String message = error.getDefaultMessage();
//                    errors.put(fieldName,message);
//                });
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//    }

}
