package com.okex.okex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@SpringBootApplication
public class OkexApplication {

	public static void main(String[] args) {
		SpringApplication.run(OkexApplication.class, args);
	}
	
	@GetMapping("/demo")
	public String demo() {
		return "Hello World!";
	}
}
