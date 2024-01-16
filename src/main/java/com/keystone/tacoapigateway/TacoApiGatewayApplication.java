package com.keystone.tacoapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
@ComponentScan(basePackages = { "com.keystone" })
public class TacoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoApiGatewayApplication.class, args);
	}

}
