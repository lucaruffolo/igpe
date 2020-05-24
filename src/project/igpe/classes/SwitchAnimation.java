package project.igpe.classes;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class SwitchAnimation extends AnimationTimer{
	private long frequency = 60 * 1000000;
	private Scene scene;
	public int x = 0;
	public int y = 0;
	public double opacity = 0.0001;
	private long previousTime;
    private Image BlackScreen = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "blackscreen.jpg"));
	public SwitchAnimation(Scene ss) {
		this.setScene(ss);
	
	}
	
	public void handle(long currentNanoTime) {		
		if (currentNanoTime - previousTime >= frequency) {
		
		//	GraphicsGame.canvasTransition.getGraphicsContext2D().fillRect(0,0, 1280,900);
			GraphicsGame.canvasTransition.getGraphicsContext2D().drawImage(BlackScreen, 1280, 900);
			GraphicsGame.canvasTransition.getGraphicsContext2D().setGlobalAlpha(1);

			while (opacity < 1){
				opacity += 0.00001;				
				//GraphicsGame.canvasTransition.getGraphicsContext2D().drawImage(BlackScreen, 1280, 900);
				GraphicsGame.canvasTransition.getGraphicsContext2D().setGlobalAlpha(opacity);
				System.out.println(opacity);					
			}
			try {
				Thread.sleep(1000);	
				System.out.println("we");
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			loadPausa.load();
		}
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

}