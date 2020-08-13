package project.igpe.classes;

import javafx.animation.AnimationTimer;
import project.igpe.GUI.DeathScene;

public class GameLoop extends AnimationTimer {

	private long previousTime;
	private GraphicsGame gg;
	//private long frequency = 60 * 1000000;
	
	public GameLoop(GraphicsGame g) {
		gg = g;
		previousTime = 0;
	}

	public void handle(long currentNanoTime) {		
		
		if (currentNanoTime - previousTime >= 1000000) {
			
			//------BulletHero
			for (int i = 0; i<Hero.getContenitoreBullets().size(); i++) {
				Bullet b = Hero.getContenitoreBullets().get(i); 
				b.moveBullet();
				if (!b.alive)
					Hero.getContenitoreBullets().remove(b);
			}
			//Fine BulletHero------
			
			
			gg.draw();
	//		gg.drawSprite();
			
			
			Hero.moveHero();
			
			

			//------Death Scene
			if (Hero.getLife()<0 ) {	//&& Singleplayer = true
				try {
					DeathScene.Rip();
					} catch (Exception e) {	e.printStackTrace();}				
			}
			//Fine Death------
			
			
			previousTime = currentNanoTime;
		}
		
		
	}
	
}

