package com.formacionspring.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =@Info(title ="Api-Registradora",version="1.0",description ="Crud completo api restfull"))
public class ApirestProductoRegistradoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestProductoRegistradoraApplication.class, args);
	}

}
