package project.igpe.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GraphicsGame extends StackPane{
	
	private Canvas canvas;
	
	private Movement movimento;
	
	private boolean isBlack = false;
	
	public GraphicsGame(Movement movimento) {
		this.movimento = movimento;
		canvas = new Canvas();
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new MovementControl(movimento, this));
		getChildren().add(canvas);
		movimento.setGraphicGame(this);
	
		
//		Carica e disegna lo sfondo!	
		try {
			setBg(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());        
	}
	
	public void setBg(int index) throws Exception {
		Maps.setIndiceMappe(index);
		Maps.loadMap(index);
		Image caricaSfondo = null;
		try {
			
			caricaSfondo = new Image (new FileInputStream(Maps.getContenitoreImg(Maps.getIndiceMappe())));
			BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo,  
	                BackgroundRepeat.NO_REPEAT,  
	                BackgroundRepeat.NO_REPEAT,  
	                BackgroundPosition.DEFAULT,  
	                   BackgroundSize.DEFAULT); 
			
			Background sfondo = new Background(backgroundimage);
			
			this.setBackground(sfondo);
			
		//	System.out.println("Cambio sfondo mappa" + Maps.getContenitoreImg(Maps.getIndiceMappe()));
			
		} catch (FileNotFoundException e) {
			System.out.println("JPG - Sfondo non trovato");
			e.printStackTrace();
		}

	}

	
	public void draw() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		if (!isBlack) {
			for (int i = 0; i < movimento.getRoom().getCella().length; i++) {
				int x = i * Settings.block;
				for (int j = 0; j < movimento.getRoom().getCella()[i].length; j++) {
					int y = j * Settings.block;

					switch (movimento.getRoom().getCella()[i][j].getType()) {
					/*	
						case Cell.WALL:
							canvas.getGraphicsContext2D().setFill(Color.BLUE);
							canvas.getGraphicsContext2D().fillRect(x+Settings.block/15, y, Settings.block*1, Settings.block*1);						
							break;
					*/
					case Cell.HEAL:
						canvas.getGraphicsContext2D().setFill(Color.RED);
						Font font = new Font("Verdana", 20);
						canvas.getGraphicsContext2D().setFont(font);
						canvas.getGraphicsContext2D().fillText("Vita: " + Hero.getLife() + "%", 50, 50);
						break;

					case Cell.OBSTACLE:
						canvas.getGraphicsContext2D().setFill(Color.RED);
						//	canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), x, y, Settings.block,Settings.block);				
						canvas.getGraphicsContext2D().fillRect(x + Settings.block / 15, y, Settings.block * 1,
								Settings.block * 1);
						break;
					case Cell.OBSTACLEDAMAGE:
						canvas.getGraphicsContext2D().setFill(Color.GRAY);
						canvas.getGraphicsContext2D().fillRect(x + Settings.block / 15, y, Settings.block * 1,
								Settings.block * 1);
						break;
					case Cell.FALLINGDOWN:
						canvas.getGraphicsContext2D().setFill(Color.BLACK);
						canvas.getGraphicsContext2D().fillRect(x + Settings.block / 15, y, Settings.block * 1,
								Settings.block * 1);
						break;
					default:
						break;
					}
					if (movimento.getPg().getX() == i && movimento.getPg().getY() == j)
						canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), x, y, Settings.block,
								Settings.block);

				}
			} 
		}
		
	}
	
	public void switchRoom () {
		
		Image caricaSfondo = new Image ("project/igpe/images/blackscreen.jpg");
		BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo,  
		        BackgroundRepeat.NO_REPEAT,  
		        BackgroundRepeat.NO_REPEAT,  
		        BackgroundPosition.DEFAULT,  
		           BackgroundSize.DEFAULT); 
		
		Background sfondo = new Background(backgroundimage);
		
		this.setBackground(sfondo);
		
		isBlack=true;
		
		Sound.musicPause();
		String LadderEffect = "src/project/igpe/sounds/apertura_porta.wav";
		Effects.setEffects(LadderEffect);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();
		while(Effects.audioclip.isPlaying()) {
		}
		isBlack=false;
		Sound.musicStart();
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	
	
	

	
}
