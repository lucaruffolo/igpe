package project.igpe.classes;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

	private long previousTime;
	private GraphicsGame gg;
	private long frequency = 60 * 1000000;

	public GameLoop(GraphicsGame g) {
		gg = g;
		previousTime = 0;
	}

	public void handle(long currentNanoTime) {		
		
		if (currentNanoTime - previousTime >= 1000000) {
			for (Bullet b : Hero.getContenitoreBullets()) {
				b.moveBullet();
			} 
		}
		if (currentNanoTime - previousTime >= frequency) {			
			gg.draw();
			previousTime = currentNanoTime;
		}
		
	}
	
}

