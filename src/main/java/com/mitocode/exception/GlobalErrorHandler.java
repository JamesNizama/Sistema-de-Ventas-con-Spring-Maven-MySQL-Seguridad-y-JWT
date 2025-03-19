package com.mitocode.exception;

import com.mitocode.dto.GenericResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest req) {
//        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleDefultException(Exception ex, WebRequest req) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(ModelNotFoundException.class)
//    public ResponseEntity<CustomErrorResponse> handleNoFoundModelException(ModelNotFoundException ex, WebRequest req) {
//        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleNoFoundModelException(ModelNotFoundException ex, WebRequest req) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(400, "Error", Arrays.asList(errorResponse)), HttpStatus.NOT_FOUND);
    }

//    //Desde spring booot +3
//    @ExceptionHandler(ModelNotFoundException.class)
//    public ProblemDetail handleNoFoundModelException(ModelNotFoundException ex, WebRequest req){
//        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        pd.setTitle("Model Not Found");

    /// /        pd.setType(URI.create(req.getContextPath()));
//        pd.setProperty("code", 404);
//        pd.setProperty("message", "NOT-FOUND");
//        return pd;
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<CustomErrorResponse> handleMethodNoFoundModelException(MethodArgumentNotValidException ex, WebRequest req) {
//        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
