package project.igpe.classes;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import project.igpe.main.Main;

public class SwitchAnimation extends AnimationTimer{
	private long frequency = 60 * 1000000;
	private Scene scene;
	public int x = 0;
	public int y = 0;
	public double opacity = 0.0001;
	private long previousTime;
    
	public SwitchAnimation(Scene ss) {
		this.setScene(ss);
	
	}
	
	public void handle(long currentNanoTime) {		
		if (currentNanoTime - previousTime >= frequency) {

			GraphicsGame.canvasTransition.getGraphicsContext2D().setFill(Color.GOLD);
			GraphicsGame.canvasTransition.getGraphicsContext2D().fillRect(100, 110, 110,110);
			while (opacity < 1){
				opacity += 0.00001;
				System.out.println(opacity);					
			}
			try {
				Thread.sleep(1000);	
				System.out.println("we");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("fine");
			Main.pauseGame();
			//loadPausa.load();

		}
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

}
/*
	@Override
	public void handle(long currentNanoTime) {
		if (currentNanoTime - previousTime >= frequency) {
			if (!GraphicsGame.isTransition()) {
				GraphicsGame.canvasTransition.getGraphicsContext2D().clearRect(0, 0, GraphicsGame.canvasTransition.getWidth(), GraphicsGame.canvasTransition.getHeight());
			}
			else  {
				for (int i = 0; i < 10; i++) {
					int x = i * Settings.block;
					for (int j = 0; j < 10; j++) {
						int y = j * Settings.block;
						GraphicsGame.canvasTransition.getGraphicsContext2D().fillRect(x, y, Settings.block*2, Settings.block*2);	
					}
				}
			}
		}
		
	}
*/
