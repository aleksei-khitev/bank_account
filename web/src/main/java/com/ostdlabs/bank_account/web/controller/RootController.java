package com.ostdlabs.bank_account.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

/** Spring MVC main controller. */
@Controller
@Scope(value = SCOPE_SESSION)
@RequestMapping(value = "/", method = RequestMethod.GET, produces = {"text/plain;charset=UTF-8"})
public class RootController{

    /** For showing start page.  */
    @RequestMapping(value = "/")
    public ModelAndView propertiesTab() {
        final ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

}
