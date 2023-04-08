package org.springframework.desafio.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class DesafioDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDbApplication.class, args);
	}

}
