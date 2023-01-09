package com.study.p6_6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class P66Application {

    public static void main(String[] args) {
        SpringApplication.run(P66Application.class, args);
    }

}
