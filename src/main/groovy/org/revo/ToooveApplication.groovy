package org.revo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class ToooveApplication {

	static void main(String[] args) {
		SpringApplication.run ToooveApplication, args
	}
}
