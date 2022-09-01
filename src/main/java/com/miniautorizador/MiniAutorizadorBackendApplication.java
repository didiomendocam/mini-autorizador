package com.miniautorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MiniAutorizadorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAutorizadorBackendApplication.class, args);
	}

}
