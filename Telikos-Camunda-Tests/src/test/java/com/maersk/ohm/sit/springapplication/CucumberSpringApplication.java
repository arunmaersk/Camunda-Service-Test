package com.maersk.ohm.sit.springapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"helper", "com.maersk.ohm.sit"},
        exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CucumberSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(CucumberSpringApplication.class,args);
    }
}
