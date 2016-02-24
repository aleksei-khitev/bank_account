package com.ostdlabs.bank_account.core.config;

import com.ostdlabs.bank_account.core.service.SystemUtil;
import com.ostdlabs.bank_account.core.service.XlsUtil;
import com.ostdlabs.bank_account.core.service.impl.SystemUtilImpl;
import com.ostdlabs.bank_account.core.service.impl.XlsUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Конфигурация для загрузчика Spring.
 * Определяет реализации интерфейсов для бинов, предназначенных для работы с базой данных.
 */
@Configuration
public class CoreSpringConfig {

    @Bean
    public SystemUtil systemUtil(){
        return new SystemUtilImpl();
    }

    @Bean
    public DateFormat dateFormat(){
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @Bean
    public XlsUtil xlsUtil() {
        return new XlsUtilImpl();
    }
}
