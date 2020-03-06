package io.forbiddenbot.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
@EnableScheduling
public class JobScheduler {
	
	@Autowired
	private ForbiddenService forbiddenService;

	
	@Scheduled(fixedRate=3600000)
    public void generateForbidden() {
		forbiddenService.generateNewForbidden();
    }
	
private final OkHttpClient httpClient = new OkHttpClient();
	
	@Scheduled(fixedDelay = 1500000)
	 public void monsterReborn() throws IOException {
		

		Request request = new Request.Builder()
                .url("https://forbidden-bot.herokuapp.com/revive")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }

		
   }
	
}
