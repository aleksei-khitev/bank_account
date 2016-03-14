package com.ostdlabs.bank_account.web.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

/** For handling exceptions from servicies. */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** Handling exceptions from database. */
    @ExceptionHandler(value = {PersistenceException.class, EmptyResultDataAccessException.class})
    public ModelAndView handleDataError(Exception exception) {
        return prepareErrorModel(exception, "A data error has occurred. contact your administrator");
    }

    /** Handling unknown exceptions/errors. */
    @ExceptionHandler(value = {Throwable.class})
    public ModelAndView handleDataError(Throwable throwable) {
        return prepareErrorModel(throwable, "An unknown error has occurred. contact your administrator");
    }

    /** Redirectiong to error page. */
    private ModelAndView prepareErrorModel(Throwable throwable, String message) {
        final ModelAndView model = new ModelAndView();
        model.setViewName("error");
        model.addObject("title", message);
        model.addObject("exception", throwable);
        return model;
    }
}
