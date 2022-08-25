package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
	
@SpringBootApplication
@ComponentScan({ "com.example" })
public class ProductManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
		
		
	}

}
