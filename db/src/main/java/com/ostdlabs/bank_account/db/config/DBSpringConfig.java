package com.ostdlabs.bank_account.db.config;

import com.ostdlabs.bank_account.db.repository.AccountRepository;
import com.ostdlabs.bank_account.db.entity.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/** Database config. */
@Configuration
@EnableJpaRepositories("com.ostdlabs.bank_account.db.repository")
@EnableTransactionManagement
@ComponentScan(basePackageClasses={DBSpringConfig.class, AccountRepository.class})
public class DBSpringConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBSpringConfig.class);
    private static final String DB_NAME = "classpath:bank_db";
    private static final String DB_DDL = "classpath:db/init/db_ddl.sql";
    private static final String INIT_DATA_SQL = "classpath:db/main.sql";
    private static final String DIALECT = "org.hibernate.dialect.HSQLDialect";
    private static final String SHOW_SQL = "false";
    private static final String FORMAT_SQL = "false";

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .setName(DB_NAME).addScript(DB_DDL).addScript(INIT_DATA_SQL);
        return builder.build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(AccountEntity.class.getPackage().getName());
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    private Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("db.hibernate.dialect", DIALECT);
        properties.put("db.hibernate.show_sql", SHOW_SQL);
        properties.put("db.hibernate.format_sql", FORMAT_SQL);
        return properties;
    }

}
