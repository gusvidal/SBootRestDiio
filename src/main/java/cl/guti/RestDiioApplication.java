package cl.guti;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;

import org.springframework.context.annotation.Bean;

import cl.guti.mapper.DiioModelMapper;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestDiioApplication {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public DiioModelMapper diioModelMapper() {
		return new DiioModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestDiioApplication.class, args);
	}

}
