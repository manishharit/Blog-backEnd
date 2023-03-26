package com.maniBlog.BlogbackEnd;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring-Boot Blog-Rest Api Collection",
				description = "Rest Api documentation with validations ",
				version = "v1.0",
				contact = @Contact(
						name = "Manish Harit",
						email = "manishharit909@gmail.com",
						url = "https://www.youtube.com/c/ManishHarit"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.youtube.com/c/ManishHarit"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog-Backend documentation",
				url = "https://github.com/ProCoderMani/Blog-backEnd"
		)
)
public class BlogBackEndApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {SpringApplication.run(BlogBackEndApplication.class, args);}

}
