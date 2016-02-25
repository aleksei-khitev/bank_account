package com.ostdlabs.bank_account.web.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {PersistenceException.class, EmptyResultDataAccessException.class})
    public ModelAndView handleDataError(Exception exception) {
        return prepareErrorModel(exception, "A data error has occurred. contact your administrator");
    }

    @ExceptionHandler(value = {Throwable.class})
    public ModelAndView handleDataError(Throwable throwable) {
        return prepareErrorModel(throwable, "An unknown error has occurred. contact your administrator");
    }

    private ModelAndView prepareErrorModel(Throwable throwable, String message) {
        final ModelAndView model = new ModelAndView();
        model.setViewName("error");
        model.addObject("title", message);
        model.addObject("exception", throwable);
        return model;
    }
}
