package com.ostdlabs.bank_account.web.conf;

import com.ostdlabs.bank_account.jms.config.JmsSpringConfig;
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
 * Spring MVC configuration.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = { RootController.class, AccountService.class, AccountConverter.class})
@Import({DBSpringConfig.class, JmsSpringConfig.class})
public class SpringWebConfiguration extends WebMvcConfigurerAdapter {

    /** Spring servlet config.*/
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /** Resources defenitions (js, css and other). */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");//NOPMD
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");//NOPMD
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /** Views definitions. */
    @Bean
    public UrlBasedViewResolver jspViewResolver() {
        final UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
