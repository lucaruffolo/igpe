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
	private Canvas canvasTransition;
	private static boolean Transition=false;
	
	private Movement movimento;
	private boolean isBlack = false;
	
	public GraphicsGame(Movement movimento) {
		this.movimento = movimento;
		canvas = new Canvas();
		
		canvasTransition = new Canvas();
		
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new MovementControl(movimento));
		getChildren().add(canvas);
		
		canvasTransition.setFocusTraversable(true);
		canvasTransition.setOnKeyPressed(new MovementControl(movimento));
		getChildren().add(canvasTransition);
		movimento.setGraphicGame(this);
	
		
		try {
			setBg(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
        
        canvasTransition.widthProperty().bind(this.widthProperty());
        canvasTransition.heightProperty().bind(this.heightProperty());

	}
	
	public void setBg(int index) throws Exception {
		Maps.setIndiceMappe(index);
		Maps.loadMap(index);
		Image caricaSfondo = null;
		try {
			
			caricaSfondo = new Image (new FileInputStream(Maps.getContenitoreImg(Maps.getIndiceMappe())));
			BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT); 
			Background sfondo = new Background(backgroundimage);
			
			this.setBackground(sfondo);
			
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
						
					default:
						break;
					}
					if (movimento.getPg().getX() == i && movimento.getPg().getY() == j)
						canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), x, y, Settings.block,Settings.block);
					

				}
			}
			for(Bullet b:Hero.getContenitoreBullets()) {
				canvas.getGraphicsContext2D().drawImage(b.getImgBulletDX(), b.getPosX(), b.getPosY(), Settings.block/2,Settings.block/2);
			}
			
			//del
			if (!Transition) {
				canvasTransition.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			}
			else  {
				for (int i = 0; i < movimento.getRoom().getCella().length; i++) {
					int x = i * Settings.block;
					for (int j = 0; j < movimento.getRoom().getCella()[i].length; j++) {
						int y = j * Settings.block;
						canvas.getGraphicsContext2D().setFill(Color.BLUE);
						canvasTransition.getGraphicsContext2D().setEffect(getEffect());
						canvasTransition.getGraphicsContext2D().fillRect(x+Settings.block/15, y+Settings.block/15, Settings.block*1, Settings.block*1);						

					}
				}
			}
			
			//del
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

	
	public static boolean isTransition() {
		return Transition;
	}

	public static void setTransition(boolean transition) {
		Transition = transition;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	
	
	

	
}
