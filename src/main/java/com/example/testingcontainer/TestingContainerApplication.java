package com.example.testingcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class TestingContainerApplication {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost/userDB");
        dataSourceBuilder.username("aston");
        dataSourceBuilder.password("aston");
        return dataSourceBuilder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestingContainerApplication.class, args);
    }

}
