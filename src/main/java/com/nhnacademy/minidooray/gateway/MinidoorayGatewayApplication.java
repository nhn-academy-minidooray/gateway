package com.nhnacademy.minidooray.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MinidoorayGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinidoorayGatewayApplication.class, args);
	}

}
