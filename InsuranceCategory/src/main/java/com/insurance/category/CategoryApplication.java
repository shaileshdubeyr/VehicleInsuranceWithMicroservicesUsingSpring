package com.insurance.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("com.insurance.category")
@EnableEurekaClient
public class CategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryApplication.class, args);
	}

}
