package project.igpe.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GraphicsGame extends StackPane{
	
	private static Canvas canvas;
	public static boolean firstRoom = true;
	private static EventHandler<KeyEvent> keyHandler;
	private static Movement movimento;
	private static Image[] imagesObstacle;
	private static Image heart;
	
	public GraphicsGame(Movement movimentox) {
		
		heart = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "heart.gif"));

	
		imagesObstacle = new Image[] {		
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs1.png")), 
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs2.png")), 
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs3.png"))
					};
		movimento = movimentox;
		
		canvas = new Canvas();
		canvas.setFocusTraversable(true);
		keyHandler=new MovementControl(movimento);
		canvas.setOnKeyPressed(keyHandler);
		canvas.setOnKeyReleased(keyHandler);
		
		getChildren().add(canvas);			
		movimento.setGraphicGame(this);
	
		if(!firstRoom) {
			try {
				setBg(Movement.getnRand());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				spawnBg();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
	}
	
	
	public void spawnBg() throws Exception {
		Image caricaSfondo = null;
		try {
			
			caricaSfondo = new Image (new FileInputStream(Maps.getFirstRoomImg()));
			BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT); 
			Background sfondo = new Background(backgroundimage);
			
			this.setBackground(sfondo);
			
		} catch (FileNotFoundException e) {
			System.out.println("JPG - Sfondo non trovato");
			e.printStackTrace();
		}

	}
	
	public void setBg(int index) throws Exception {
		
		if (Movement.isDoorDown()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getUpImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorDx()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getLeftImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorLx()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getRightImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorUp()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getDownImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}

	}
	
	public void draw() {
		
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());		
		canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), Hero.getX(), Hero.getY(), Hero.getSize(), Hero.getSize());
		
		// HEALTH BAR
		//Background Vita
		canvas.getGraphicsContext2D().setFill(Color.BLACK);
		canvas.getGraphicsContext2D().fillRect(40, 20, 210, 40);
				
		if (Hero.getLife()>67)
			canvas.getGraphicsContext2D().setFill(Color.LIME);
		if (Hero.getLife()<66)
			canvas.getGraphicsContext2D().setFill(Color.YELLOW);
		if (Hero.getLife()<34)
				canvas.getGraphicsContext2D().setFill(Color.RED);		
		
		canvas.getGraphicsContext2D().fillRect(45, 25, Hero.getLife()*2, 30); //Hero.getLife()*2 perchè barra lunga 200
		
		
		// TESTO
		canvas.getGraphicsContext2D().setFill(Color.LIGHTBLUE);
		Font font = new Font("Verdana", 20);
		canvas.getGraphicsContext2D().setFont(font);
		canvas.getGraphicsContext2D().fillText("Vita: " + (int) Hero.getLife() + "%", 100, 50);		
		
		//FINE HEALTH BAR
	
		
		// Contatore bulletsHero
		canvas.getGraphicsContext2D().setFill(Color.GREEN);
		canvas.getGraphicsContext2D().setFont(font);
		canvas.getGraphicsContext2D().fillText("Colpi Rimanenti: " + (Bullet.maxAmmo-Bullet.heroAmmo), 45, 100);
		
		// bullets Hero
		for(Bullet b:Hero.getContenitoreBullets()) {
			canvas.getGraphicsContext2D().drawImage(b.getImgBulletDX(), b.getPosX(), b.getPosY(), Settings.block/2,Settings.block/2);
		}
		
		
		
		for (int i = 0; i < Settings.xMatrix; i++) {
			for (int j = 0; j < Settings.yMatrix; j++) {				
				switch (movimento.getRoom().getCella()[i][j].getType()) {						
				case Cell.OBSTACLE:
					canvas.getGraphicsContext2D().setFill(Color.BLUE);
				//	int nRand = (int) (2.0 * Math.random());
					canvas.getGraphicsContext2D().drawImage(imagesObstacle[1], Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Hero.getSize(), Hero.getSize());
				//	canvas.getGraphicsContext2D().fillRect(Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Settings.block*1, Settings.block*1);						
					break;		
/*				case Cell.DOOR:
					canvas.getGraphicsContext2D().setFill(Color.YELLOW);
					canvas.getGraphicsContext2D().fillRect(Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Settings.block*1, Settings.block*1);						
					break;	
	*/			case Cell.OBSTACLEDAMAGE:
					canvas.getGraphicsContext2D().setFill(Color.RED);
					canvas.getGraphicsContext2D().fillRect(Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Settings.block*1, Settings.block*1);						
					break;
				case Cell.HEART:
					canvas.getGraphicsContext2D().drawImage(heart, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), 50, 45);						
					break;
				default:
					break;					
				}
			}
		}
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvasx) {
		canvas = canvasx;
	}


	public static boolean getFirstRoom() {
		return firstRoom;
	}


	public static void setFirstRoom(boolean firstRoom) {
		GraphicsGame.firstRoom = firstRoom;
	}

	
	
}
