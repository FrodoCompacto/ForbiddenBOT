package io.forbiddenbot;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import io.forbiddenbot.services.ForbiddenService;


@SpringBootApplication
public class BotspringApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(BotspringApplication.class, args);
	}
	
	@Autowired
	private ForbiddenService forbiddenService;

	@Override
	public void run(String... args) {

		
		forbiddenService.generateNewForbidden();

	}

}
