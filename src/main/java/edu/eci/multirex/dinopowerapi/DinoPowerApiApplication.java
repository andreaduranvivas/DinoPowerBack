package edu.eci.multirex.dinopowerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.multirex.dinopower"})
public class DinoPowerApiApplication {
    
    public static void main(String[] args) {
		SpringApplication.run(DinoPowerApiApplication.class, args);
	}

}
