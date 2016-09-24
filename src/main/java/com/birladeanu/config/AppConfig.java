package com.birladeanu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by pavel on 8/28/16.
 */
@Configuration
@EnableJpaRepositories("com.birladeanu.dal.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.birladeanu.service", "com.birladeanu.dal.dao"})
@PropertySource(value = { "classpath:database.properties" })
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.birladeanu.dal.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("dialect", environment.getRequiredProperty("jdbc.dialect"));
        connectionProperties.setProperty("c3p0.min_size", environment.getRequiredProperty("c3p0.min_size"));
        connectionProperties.setProperty("c3p0.max_size", environment.getRequiredProperty("c3p0.max_size"));
        connectionProperties.setProperty("c3p0.timeout", environment.getRequiredProperty("c3p0.timeout"));
        connectionProperties.setProperty("c3p0.max_statements", environment.getRequiredProperty("c3p0.max_statements"));
        connectionProperties.setProperty("c3p0.idle_test_period", environment.getRequiredProperty("c3p0.idle_test_period"));
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/rest");
            }

        };
    }
}
