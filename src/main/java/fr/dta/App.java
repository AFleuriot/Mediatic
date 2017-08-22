package fr.dta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ComponentScan
@EnableAutoConfiguration
//@ImportResource("classpath:persistence.xml")
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	

}
