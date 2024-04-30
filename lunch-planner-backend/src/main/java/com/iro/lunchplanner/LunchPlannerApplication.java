package com.iro.lunchplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LunchPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchPlannerApplication.class, args);
	}

}
