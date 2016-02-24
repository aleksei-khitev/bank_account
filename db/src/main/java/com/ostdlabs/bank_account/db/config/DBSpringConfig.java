package com.ostdlabs.bank_account.db.config;

import com.ostdlabs.bank_account.db.pojo.organizations.*;
import com.ostdlabs.bank_account.db.pojo.tssbase.Listname;
import com.ostdlabs.bank_account.db.qualifier.Organizations;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.ostdlabs.bank_account.db.dao.ListNameDao;
import com.ostdlabs.bank_account.db.dao.UserDao;
import com.ostdlabs.bank_account.db.qualifier.TssBase;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация для загрузчика Spring.
 * Определяет реализации интерфейсов для бинов,
 * предназначенных для работы с базой данных.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses={DBSpringConfig.class, ListNameDao.class, UserDao.class})
public class DBSpringConfig {
    /** Системный журнал. slf4j и log4j */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DBSpringConfig.class);
    /** Драйвер подключения к базе данных */
    public static final String DRIVER_CLASS_NAME = "org.firebirdsql.jdbc.FBDriver";
    /** Путь к базе данных TSSBASE */
    public static final String TSSBASE_URL = "jdbc:firebirdsql:appserv-dev:tssbase?encoding=WIN1251";
    /** Путь к базе данных ORGANIZATIONS */
    public static final String ORGANIZATION_URL = "jdbc:firebirdsql:appserv-dev:organization?encoding=WIN1251";
    /** Логин доступа к базам данных */
    public static final String USERNAME = "SYSDBA";
    /** Пароль доступа к базам данных */
    public static final String PASSWORD = "44215";
    /** Диалект тестовой базы данных */
    public static final String DIALECT = "org.hibernate.dialect.FirebirdDialect";
    /** Показывать sql. Параметр hibernate */
    public static final String SHOW_SQL = "false";
    /** Форматировать sql. Параметр hibernate */
    public static final String FORMAT_SQL = "false";

    /**
     * Предоставляет фабрику сессий для базы данных TSSBASE
     * @param tssBaseDS Источник данных
     * @return Фабрика сессий
     */
    @Bean
    @TssBase
    public SessionFactory tssbaseSF(final DataSource tssBaseDS) {
        final AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(tssBaseDS);
        sessionFactory.setAnnotatedClasses(Listname.class);
        sessionFactory.setHibernateProperties(hibernateProperties());
        try{
            sessionFactory.afterPropertiesSet();
        }catch (Exception e){//NOPMD
            LOGGER.error("Ошибка в конфигурационном файле Spring. ", e);
        }
        return sessionFactory.getObject();
    }

    /**
     * Источник данных для базы данных TSSBASE
     * @return Источник данных
     */
    @Bean
    public DataSource tssBaseDS() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(TSSBASE_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    /**
     * Предоставляет фабрику сессий для базы данных ORGANIZATIONS
     * @param organizationsDS Источник данных
     * @return Фабрика сессий
     */
    @Bean
    @Organizations
    public SessionFactory organizationsSF(final DataSource organizationsDS) {
        final AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(organizationsDS);
        sessionFactory.setAnnotatedClasses(User.class, Group.class, Subscription.class, SubscriptionsType.class,
                Office.class, StatementBaseType.class, StatementBase.class, Statement.class);
        sessionFactory.setHibernateProperties(hibernateProperties());
        try{
            sessionFactory.afterPropertiesSet();
        }catch (Exception e){//NOPMD
            LOGGER.error("Ошибка в конфигурационном файле Spring. ", e);
        }
        return sessionFactory.getObject();
    }

    /**
     * Источник данных для базы данных ORGANIZATIONS
     * @return Источник данных
     */
    @Bean
    public DataSource organizationsDS() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(ORGANIZATION_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    /**
     * Менеджер транзакций для управления соединениями с базой данных ORGANIZATIONS
     * @param organizationsSF Фабрика соединений
     * @return Менеджер транзакций
     */
    @Bean
    public HibernateTransactionManager transactionManager(final SessionFactory organizationsSF) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(organizationsSF);
        return txManager;
    }

    /**
     * Возвращает свойства для hibernate, такие как диалект.
     * @return Свойства для hibernate
     */
    private Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", DIALECT);
        properties.put("hibernate.show_sql", SHOW_SQL);
        properties.put("hibernate.format_sql", FORMAT_SQL);
        return properties;
    }
}
