package com.belrose.writereposervice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "RepoWrite Open APIs Service",version = "v1",
		description = "RepoWrite Open APIs for analyse some task",
		license = @License(name = "Apache 2.0",url = "https://www.apache.org/licences/LICENCE-2.0")))
public class RepoWriteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepoWriteServiceApplication.class, args);
	}

}
