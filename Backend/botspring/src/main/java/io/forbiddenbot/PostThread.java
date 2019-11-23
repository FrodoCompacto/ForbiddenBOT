package io.forbiddenbot;

import java.util.ArrayList;
import java.util.List;

public class PostThread extends Thread {
	
	public static List<String> ipList = new ArrayList<>();

	public PostThread() {
	}

	public void run() {

		while (true) {
			ipList.clear();
			try {
				sleep(30000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}
}
