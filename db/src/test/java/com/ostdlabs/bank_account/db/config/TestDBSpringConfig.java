package com.ostdlabs.bank_account.db.config;

import com.ostdlabs.bank_account.db.dao.ListNameDao;
import com.ostdlabs.bank_account.db.dao.UserDao;
import com.ostdlabs.bank_account.db.pojo.organizations.*;
import com.ostdlabs.bank_account.db.pojo.tssbase.Listname;
import com.ostdlabs.bank_account.db.qualifier.Organizations;
import com.ostdlabs.bank_account.db.qualifier.TssBase;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Конфигурация контекста Spring.<br/>
 * Включает в себя:<ul>
 * <li>создание базы данных HSQLDB</li>
 * <li>управелние сценариями SQL по наполнению базы данных</li>
 * <li>>DI элементов</li
 * </ul>
 */
@Configuration
@ComponentScan(basePackageClasses={TestDBSpringConfig.class, ListNameDao.class, UserDao.class},
        excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value = DBSpringConfig.class)
})
public class TestDBSpringConfig {
    /** Системный журнал. slf4j и log4j */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TestDBSpringConfig.class);
    /** Путь к сценарию, наполняющему tssbase */
    public static final String TSSBASE_SQL = "db/tssbase.sql";
    /** Путь к сценарию, наполняющему organizations */
    public static final String ORG_SQL = "db/organizations.sql";
    /** Название тестовой базы tssbase */
    public static final String TEST_TSSBASE = "test_tssbase";
    /** Путь к сценарию, создающему таблицы tssbase */
    public static final String TSSBASE_DDL = "db/init/test_tssbase_ddl.sql";
    /** Название тестовой базы organizations */
    public static final String TEST_ORG = "test_organizations";
    /** Путь к сценарию, создающему таблицы organizations */
    public static final String ORG_DDL = "db/init/test_organizations_ddl.sql";
    /** Диалект тестовой базы данных */
    public static final String DIALECT = "org.hibernate.dialect.HSQLDialect";
    /** Показывать sql. Параметр hibernate */
    public static final String SHOW_SQL = "true";
    /** Форматировать sql. Параметр hibernate */
    public static final String FORMAT_SQL = "true";

    /**
     * Предоставляет фабрику сессий для базы данных tssbase
     * @param tssBaseDS Источник данных
     * @return Фабрика сессий
     */
    @Bean
    @TssBase
    public SessionFactory tssBaseSessionFactory(final DataSource tssBaseDS) {
        final AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(tssBaseDS);
        sessionFactory.setAnnotatedClasses(Listname.class);
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setSchemaUpdate(true);
        try {
            sessionFactory.afterPropertiesSet();
        } catch (Exception e) {//NOPMD
            LOGGER.error("Ошибка в тестовом конфигурационном файле Spring. ", e);
        }
        return sessionFactory.getObject();
    }

    /**
     * Источник данных для базы данных tssbase
     * @return Источник данных
     */
    @Bean
    public DataSource tssBaseDS() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .setName(TEST_TSSBASE).addScript(TSSBASE_DDL).addScript(TSSBASE_SQL);
        return builder.build();
    }

    /**
     * Предоставляет фабрику сессий для базы данных organizations
     * @param organizationsDS Источник данных
     * @return Фабрика сессий
     */
    @Bean
    @Organizations
    public SessionFactory organizationsSessionFactory(final DataSource organizationsDS) {
        final AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(organizationsDS);
        sessionFactory.setAnnotatedClasses(User.class, Group.class, Subscription.class, SubscriptionsType.class,
                Office.class, StatementBaseType.class, StatementBase.class, Statement.class);
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setSchemaUpdate(true);
        try{
            sessionFactory.afterPropertiesSet();
        } catch (Exception e) {//NOPMD
            LOGGER.error("Ошибка в тестовом конфигурационном файле Spring. ", e);
        }
        return sessionFactory.getObject();
    }

    /**
     * Источник данных для базы данных organizations
     * @return Источник данных
     */
    @Bean
    public DataSource organizationsDS() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .setName(TEST_ORG).addScript(ORG_DDL).addScript(ORG_SQL);
        return builder.build();
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
