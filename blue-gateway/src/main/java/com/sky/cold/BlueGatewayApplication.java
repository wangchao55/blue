package com.sky.cold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlueGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueGatewayApplication.class, args);
    }

}
