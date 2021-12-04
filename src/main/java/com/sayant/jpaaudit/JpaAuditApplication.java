package com.sayant.jpaaudit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAuditApplication.class, args);
	}

}
