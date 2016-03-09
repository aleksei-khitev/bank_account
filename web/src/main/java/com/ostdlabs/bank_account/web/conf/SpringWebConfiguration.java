package com.ostdlabs.bank_account.web.conf;

import com.ostdlabs.bank_account.core.config.CoreSpringConfig;
import com.ostdlabs.bank_account.db.config.DBSpringConfig;
import com.ostdlabs.bank_account.web.controller.RootController;
import com.ostdlabs.bank_account.web.service.AccountService;
import com.ostdlabs.bank_account.web.util.AccountConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Конфигурация Spring MVC.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = { RootController.class, AccountService.class, AccountConverter.class})
@Import({DBSpringConfig.class, CoreSpringConfig.class})
public class SpringWebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Конфигурация сервлета Spring
     */
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Определение подключаемых ресурсов.
     * Если не задать тут, то не подхватятся скрипты и стили.
     * @param registry На этом объекте и производится подключение.
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");//NOPMD
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");//NOPMD
    }

    /**
     * Требуется для работы Spring
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Определение представлений.
     * @return На этом объекте и происходит определение размещения представлений.
     */
    @Bean
    public UrlBasedViewResolver jspViewResolver() {
        final UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
