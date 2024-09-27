package com.example.finally2.execption;

import com.example.finally2.execption.custom.FileExecption;
import com.example.finally2.execption.custom.ListIsEmptyExecption;
import com.example.finally2.execption.custom.NotFoundExecption;
import com.example.finally2.execption.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    Map<String, String> errors = new HashMap<>();

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex ) {
        if (!errors.isEmpty()) {
            errors.clear();
        }
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error.getCode(), new Object[]{} , LocaleContextHolder.getLocale() );
            errors.put(fieldName, errorMessage);
            errors.put("message", errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundExecption.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(NotFoundExecption ex) {
        String message = messageSource.getMessage(ex.getMessage(), null,LocaleContextHolder.getLocale());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleRequestParameterException(MissingServletRequestParameterException ex) {
        if (!errors.isEmpty()) {
            errors.clear();
        }
        String message = messageSource.getMessage("checkRequestParameter", null,LocaleContextHolder.getLocale());
        errors.put(ex.getParameterName(),message);
        errors.put("message",message);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeInputExceptions(MethodArgumentTypeMismatchException ex) {
        if (!errors.isEmpty()) {
            errors.clear();
        }

        String errorMessage = messageSource.getMessage("typeMismatch", null, LocaleContextHolder.getLocale());
        errors.put("message", errorMessage + ex.getName());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileExecption.class)
    public ResponseEntity<Map<String , String>> handleFile(FileExecption ex){
        if (!errors.isEmpty()) {
            errors.clear();
        }
        String errorMessage = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        errors.put("message", errorMessage );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String , String>> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        if (!errors.isEmpty()) {
            errors.clear();
        }
        String errorMessage = messageSource.getMessage("handleFileSize", null, LocaleContextHolder.getLocale());
        errors.put("message", errorMessage );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListIsEmptyExecption.class)
    public ResponseEntity<Map<String , String>> handleListIsEmptyExecption(ListIsEmptyExecption ex){
        String message = messageSource.getMessage(ex.getMessage(), null,  LocaleContextHolder.getLocale());
        errors.put("message",  message);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
