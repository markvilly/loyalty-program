package com.medikea.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



// @EntityScan("com.medikea.offers.domain")
// @EnableJpaRepositories("com.medikea.offers.repo")
@SpringBootApplication
public class OffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffersApplication.class, args);
	}

}
