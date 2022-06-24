package com.formacionspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =@Info(title ="Api proveedores",version="1.0",description ="Crud completo api restful"))
public class AppirestClienteDistribuidoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppirestClienteDistribuidoraApplication.class, args);
	}

}
