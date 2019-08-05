package com.pandatv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;

/**
 * @author: likaiqing
 * @create: 2019-01-24 19:11
 **/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Executors.newSingleThreadScheduledExecutor();
    }
}
