package org.visitors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.visitors")
@EntityScan("org.visitors")
public class VisitorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorsApplication.class, args);
	}

}
