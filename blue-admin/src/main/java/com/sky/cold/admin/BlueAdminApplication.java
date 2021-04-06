package com.sky.cold.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.sky.cold")
public class BlueAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueAdminApplication.class, args);
    }

}
