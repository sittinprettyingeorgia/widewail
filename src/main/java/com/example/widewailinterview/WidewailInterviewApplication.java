package com.example.widewailinterview;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@SpringBootApplication
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT)
public class WidewailInterviewApplication {
    @Autowired DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(WidewailInterviewApplication.class, args);
    }

}
