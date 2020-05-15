package project.igpe.GUI;

import project.igpe.main.Main;

public class SleepThread implements Runnable{

	@Override
	public void run() {
		while (true) {
			try {
				
				Thread.sleep(1000);
				System.out.println("in thread");
			} catch (InterruptedException e) {
			
				return;
			}
		}
		
	}
	

}
