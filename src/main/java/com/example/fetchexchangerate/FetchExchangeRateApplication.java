package com.example.fetchexchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FetchExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FetchExchangeRateApplication.class, args);
	}

}
