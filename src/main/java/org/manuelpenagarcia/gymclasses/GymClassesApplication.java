package org.manuelpenagarcia.gymclasses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GymClassesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymClassesApplication.class, args);
	}
	
	@Bean
	ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(ServerProperties serverProperties) {

		return (evt) -> {

			Integer port = serverProperties.getPort();
			
			System.out.println("Porto: " + port);
		};
	}

}
