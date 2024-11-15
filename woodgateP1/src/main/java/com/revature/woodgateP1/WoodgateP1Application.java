package com.revature.woodgateP1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.revature.woodgateP1.models")
@ComponentScan("com.revature.woodgateP1")
@EnableJpaRepositories("com.revature.woodgateP1.daos")
public class WoodgateP1Application {

	public static void main(String[] args) {

		SpringApplication.run(WoodgateP1Application.class, args);
	}

}
