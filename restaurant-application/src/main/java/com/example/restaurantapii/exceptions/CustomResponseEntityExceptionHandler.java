package com.example.restaurantapii.exceptions;

import com.example.restaurantapii.dto.ErrorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Locale;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return  slr;
    }

    @Autowired
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @ExceptionHandler(BusinessRuleException.class)
    public final ResponseEntity<ErrorResponseDTO> handlerBusinessRuleException(BusinessRuleException e, WebRequest request,Locale locale){
        ErrorResponseDTO response = prepareResponseModel(messageSource().getMessage(e.getMessage(),null,locale), request,e.getErrorCode(),locale);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handlerContentNotFoundException(ContentNotFoundException e, WebRequest request,Locale locale){
        ErrorResponseDTO response = prepareResponseModel(messageSource().getMessage(e.getMessage(),null,locale),request,e.getErrorCode(),locale);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemException.class)
    public final ResponseEntity<ErrorResponseDTO> handlerSystemException(SystemException e, WebRequest request,Locale locale){
        ErrorResponseDTO response = prepareResponseModel(messageSource().getMessage(e.getMessage(),null,locale),request,e.getErrorCode(),locale);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ErrorResponseDTO prepareResponseModel(String message,WebRequest request,Integer errorCode,Locale locale){
        return new ErrorResponseDTO(new Date(),message,request.getDescription(false),errorCode,locale);
    }
}
