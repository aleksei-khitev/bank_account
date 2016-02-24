package com.ostdlabs.bank_account.web.controller;

import com.ostdlabs.bank_account.web.service.AccountService;
import com.ostdlabs.bank_account.web.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

/**
 * Основной контроллер Spring MVC
 */
@Controller
@Scope(value = SCOPE_SESSION)
@RequestMapping(value = "/", method = RequestMethod.GET, produces = {"text/plain;charset=UTF-8"})
public class RootController{
    /** Тип ответа HTTP */
    public static final String APP_JSON_PRODUCES = "application/json";

    /** Контекст для управления сессиями */
    @Autowired
    private HttpServletRequest context;//NOPMD

    /**
     * Версия проекта.
     */
    private static final String PROJECT_VERSION = "1.0.13";

    /**
     * Cервис для работы с пользователями.
     */
    @Autowired
    private AccountService accountService;//NOPMD

    /**
     * Реагирует на вход на главную страницу.
     * @return В модели данные о пользователе, список организаций.
     */
    @RequestMapping(value = "/")
    public ModelAndView propertiesTab() {
        final ModelAndView model = new ModelAndView();
        try {
            model.setViewName("index");
            model.addObject("projectVersion", PROJECT_VERSION);
            model.addObject("accounts", new ArrayList<AccountVO>());
        }catch (Exception e) { // TODO: разобраться с ошибками в spring mvc
            model.setViewName("title");
            model.addObject("title", "Прочая ошибка.");
            model.addObject("exception", e);
        }
        return model;
    }

}
