package com.sky.cold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sky.cold.common.*")
public class BlueAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueAdminApplication.class, args);
    }

}
