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
	private static Image[] imagesObstacleDamages;
	private static Image heart;
	private static Image weapon;

	public static int nRandObstacles = 0; 
	
	public GraphicsGame(Movement movimentox) {
		
		heart = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "heart.gif"));
		weapon = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "pistol.gif"));

		imagesObstacleDamages  = new Image[] {		
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs1dmg.gif")), 
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs2dmg.gif")), 
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs1dmg.gif"))
			};
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
		
		//nRandObstacles = (int) (2.0 * Math.random());

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
	public void dirPistol(int dir) {
		if (Hero.getDirHero() == Hero.MOVE_LEFT)
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()-40, Hero.getY()-10, Hero.getSize()+20, Hero.getSize()+20);
		if (Hero.getDirHero() == Hero.MOVE_RIGHT)
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()+25, Hero.getY()-10, Hero.getSize()+20, Hero.getSize()+20);
		if (Hero.getDirHero() == Hero.MOVE_UP)
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()-10, Hero.getY()-40, Hero.getSize()+20, Hero.getSize()+20);
		if (Hero.getDirHero() == Hero.MOVE_DOWN)
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()-10, Hero.getY()+20, Hero.getSize()+20, Hero.getSize()+20);
	}
	
	
	public void draw() {	
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		if (Hero.getDirHero() == Hero.MOVE_DOWN) {
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), Hero.getX(), Hero.getY(), Hero.getSize(), Hero.getSize());
			if (Hero.takePistol)
				dirPistol(Hero.getDirHero());
		}else if (Hero.getDirHero() == Hero.MOVE_RIGHT) {
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), Hero.getX(), Hero.getY(), Hero.getSize(), Hero.getSize());
			if (Hero.takePistol)
				dirPistol(Hero.getDirHero());
		} else {
			if (Hero.takePistol)
				dirPistol(Hero.getDirHero());
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), Hero.getX(), Hero.getY(), Hero.getSize(), Hero.getSize());
		}				
		
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
		//---------FINE HEALTH BAR
		
		// Contatore bulletsHero
		if (Hero.takePistol) {
			canvas.getGraphicsContext2D().setFill(Color.GREEN);
			canvas.getGraphicsContext2D().setFont(font);
			canvas.getGraphicsContext2D().fillText("Colpi Rimanenti: " + (Bullet.maxAmmo-Bullet.heroAmmo), 45, 100);
		}
		
		// bullets Hero
		for(Bullet b:Hero.getContenitoreBullets()) {
			canvas.getGraphicsContext2D().drawImage(b.getImgBullet(), b.getPosX(), b.getPosY(), Settings.block,Settings.block);
		}
		
		
		
		for (int i = 0; i < Settings.xMatrix; i++) {
			for (int j = 0; j < Settings.yMatrix; j++) {				
				switch (movimento.getRoom().getCella()[i][j].getType()) {						
				case Cell.OBSTACLE:
					canvas.getGraphicsContext2D().drawImage(imagesObstacle[nRandObstacles], Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Hero.getSize()+10, Hero.getSize()+10);
					break;			
				case Cell.OBSTACLEDAMAGE:
					//canvas.getGraphicsContext2D().setFill(Color.RED);
					canvas.getGraphicsContext2D().drawImage(imagesObstacleDamages[nRandObstacles], Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Hero.getSize()+10, Hero.getSize()+10);
					break;
				case Cell.HEART:
					canvas.getGraphicsContext2D().drawImage(heart, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), 50, 42);						
					break;
				case Cell.PISTOL:
					if (!Hero.takePistol)
						canvas.getGraphicsContext2D().drawImage(weapon, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), 90, 80);						
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
