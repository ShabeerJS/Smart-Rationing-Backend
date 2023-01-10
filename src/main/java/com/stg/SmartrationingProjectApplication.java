package com.stg;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@SwaggerDefinition
public class SmartrationingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartrationingProjectApplication.class, args);
		
		
	}
	//dto
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigur() {
//		return new WebMvcConfigurer() {
//		
//			public void addcorsMapping(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//			}
//		};
//		
//	}

}
