package io.forbiddenbot;

import java.util.ArrayList;
import java.util.List;

public class RowThread extends Thread {
	
	public static List<String> ipList = new ArrayList<>();

	public RowThread() {
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
