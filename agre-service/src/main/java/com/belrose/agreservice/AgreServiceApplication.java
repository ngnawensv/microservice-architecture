package com.belrose.agreservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "Anal Open APIs Service",version = "v1",
		description = "Anal Open APIs for analyse some task",
		license = @License(name = "Apache 2.0",url = "https://www.apache.org/licences/LICENCE-2.0")))
public class AgreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgreServiceApplication.class, args);
	}

}
