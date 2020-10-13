package com.mx.lbg.bimbo.equiposservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
// public class EquiposServiceApplication {
public class EquiposServiceApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(EquiposServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(EquiposServiceApplication.class);
	}
}
