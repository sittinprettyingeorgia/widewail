package com.example.widewailinterview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT)
@EnableScheduling
public class WidewailInterviewApplication {
    @Autowired DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(WidewailInterviewApplication.class, args);
    }

}
