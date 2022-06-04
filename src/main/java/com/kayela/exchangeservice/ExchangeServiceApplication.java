package com.kayela.exchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.kayela.exchangeservice.api")
public class ExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeServiceApplication.class, args);
	}

}
