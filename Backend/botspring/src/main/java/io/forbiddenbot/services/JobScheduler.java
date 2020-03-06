package io.forbiddenbot.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class JobScheduler {
	
	@Autowired
	private ForbiddenService forbiddenService;

	
	@Scheduled(fixedRate=3600000)
    public void generateForbidden() {
		forbiddenService.generateNewForbidden();
    }
	

	
}
