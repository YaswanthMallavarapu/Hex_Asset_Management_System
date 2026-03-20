package com.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("org.hibernate.*")
@EnableTransactionManagement
public class ProjConfig {

    @Bean
    public DataSource dataSource(){
        var datasource=new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/asset_system_db");
        datasource.setUsername("root");
        datasource.setPassword("root");
        return datasource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

        // LocalContainerEntityManagerFactoryBean creating pojo class and setting required fields datasouce
        // components to scan for models
        //connecting spring orm to jpa
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
                =new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("org.hibernate.model");

        // connecting jpa with the hibernate
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=
                new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        //setting hibernate configurations
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
}


