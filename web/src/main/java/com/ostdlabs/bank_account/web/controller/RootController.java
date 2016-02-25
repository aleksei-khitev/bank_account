package com.ostdlabs.bank_account.web.controller;

import com.ostdlabs.bank_account.web.service.AccountService;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

/**
 * Основной контроллер Spring MVC
 */
@Controller
@Scope(value = SCOPE_SESSION)
@RequestMapping(value = "/", method = RequestMethod.GET, produces = {"text/plain;charset=UTF-8"})
public class RootController{
    private static final String PROJECT_VERSION = "1.0.13";

    @RequestMapping(value = "/")
    public ModelAndView propertiesTab() {
        final ModelAndView model = new ModelAndView();
        try {
            model.setViewName("index");
            model.addObject("projectVersion", PROJECT_VERSION);
        } catch (PersistenceException | EmptyResultDataAccessException exception) { // TODO: разобраться с ошибками в spring mvc
            model.setViewName("error");
            model.addObject("title", "A data error has occurred. contact your administrator");
            model.addObject("exception", exception);
        } catch (Throwable throwable) { // TODO: разобраться с ошибками в spring mvc
            model.setViewName("error");
            model.addObject("title", "An unknown error has occurred. contact your administrator");
            model.addObject("exception", throwable);
        }
        return model;
    }

}
