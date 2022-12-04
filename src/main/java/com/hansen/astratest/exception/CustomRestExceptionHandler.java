package com.hansen.astratest.exception;

import com.hansen.astratest.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({SystemUnavailableException.class})
    public ResponseEntity<Object> handleExceptionOther() {
        var code = HttpStatus.INTERNAL_SERVER_ERROR;
        var apiError =
                new ApiResponse("System Unvailable",false);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), code);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        errors.stream().distinct();

        var apiError =
                new ApiResponse(errors,false);

        return handleExceptionInternal(
                ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataNotFound.class})
    public ResponseEntity<Object> handleDataNotFound(DataNotFound ex) {
        var apiError =
                new ApiResponse(ex.getMessage(),false);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.OK);
    }
}
