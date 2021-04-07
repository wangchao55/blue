package com.sky.cold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sky.cold.common.*","com.sky.cold.security.*"})
public class BlueAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueAdminApplication.class, args);
    }

}
