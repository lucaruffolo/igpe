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
	private static Image imagesObstacleDamages;
	private static Image imagesFire;
	private static Image heart;
	private static Image weapon;
	public static Enemy nemico;
	public static Enemy2 nemico2;
	public static int nRandObstacles = 0;
	public static Boolean EnemySpawn = false;
	public static Boolean EnemySpawn2 = false;
	
	public GraphicsGame(Movement movimentox) {
		
		heart = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "heart.gif"));
		weapon = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "pistol.gif"));

		imagesObstacleDamages  = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs1dmg.gif"));
		imagesFire = new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "obs2dmg.gif"));
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
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()+37, Hero.getY()-5, Hero.getSize()+20, Hero.getSize()+20);
		if (Hero.getDirHero() == Hero.MOVE_UP)
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()-10, Hero.getY()-40, Hero.getSize()+20, Hero.getSize()+20);
		if (Hero.getDirHero() == Hero.MOVE_DOWN)
			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImgPistol(), Hero.getX()-10, Hero.getY()+20, Hero.getSize()+20, Hero.getSize()+20);
	}
	
	public void draw() {	
		
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());	
		// HEALTH BAR
		//Background Vita
		canvas.getGraphicsContext2D().setFill(Color.BLACK);
		canvas.getGraphicsContext2D().fillRect(40, 20, 210, 40);
				
		if (Hero.getLife()>=67)
			canvas.getGraphicsContext2D().setFill(Color.LIME);
		if (Hero.getLife()<=66)
			canvas.getGraphicsContext2D().setFill(Color.YELLOW);
		if (Hero.getLife()<=34)
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
		
		if (BulletEnemy.isAlive())
			canvas.getGraphicsContext2D().drawImage(Enemy.bullet.getImgBullet(), Enemy.bullet.getPosX(), Enemy.bullet.getPosY(), Settings.block,Settings.block);
		if (BulletEnemy2.isAlive())
			canvas.getGraphicsContext2D().drawImage(Enemy2.bullet.getImgBullet(), Enemy2.bullet.getPosX(), Enemy2.bullet.getPosY(), Settings.block,Settings.block);
		
		
		for (int i = 0; i < Settings.xMatrix; i++) {
			for (int j = 0; j < Settings.yMatrix; j++) {				
				switch (movimento.getRoom().getCella()[i][j].getType()) {						
				case Cell.OBSTACLE:
					canvas.getGraphicsContext2D().drawImage(imagesObstacle[nRandObstacles], Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Hero.getSize()+10, Hero.getSize()+10);
					break;			
				case Cell.OBSTACLEDAMAGE:
					canvas.getGraphicsContext2D().drawImage(imagesObstacleDamages, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Hero.getSize()+10, Hero.getSize()+10);
					break;
				case Cell.FIRE:
					canvas.getGraphicsContext2D().drawImage(imagesFire, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Hero.getSize()+10, Hero.getSize()+10);
					break;
				case Cell.HEART:
					canvas.getGraphicsContext2D().drawImage(heart, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), 50, 42);						
					break;
				case Cell.PISTOL:
					if (!Hero.takePistol)
						canvas.getGraphicsContext2D().drawImage(weapon, Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), 90, 80);						
					break;
				case Cell.ENEMY: 

					if (!EnemySpawn) {
						nemico = new Enemy(Movement.matrixInPixelX(i),Movement.matrixInPixelY(j));
						nemico.isAlive = true;
						EnemySpawn = true;
						System.out.println("Spawno nemico");
					}
					if (nemico.getLife()>0)
						canvas.getGraphicsContext2D().drawImage(GraphicEnemy.getImg(), nemico.getX(), nemico.getY(), Enemy.getSize()+20, Enemy.getSize()+20);
					else {

						nemico.isAlive = false;
					}
					
					//Vita upper enemy
					if (nemico.getLife()>0) {
						canvas.getGraphicsContext2D().setFill(Color.BLACK);
						canvas.getGraphicsContext2D().fillRect(nemico.getX(), nemico.getY()-10,  200/3, 5); //200 Life Max enemy
								
						if (nemico.getLife()>132)
							canvas.getGraphicsContext2D().setFill(Color.LIME);
						if (nemico.getLife()<132)
							canvas.getGraphicsContext2D().setFill(Color.YELLOW);
						if (nemico.getLife()<66)
							canvas.getGraphicsContext2D().setFill(Color.RED);		
						
						canvas.getGraphicsContext2D().fillRect(nemico.getX(), nemico.getY()-10, nemico.getLife()/3, 5); //Hero.getLife()*2 perchè barra lunga 200
					}
					//fine vita upper enemy
					break;
				case Cell.ENEMY2: 

					if (!EnemySpawn2) {
						nemico2 = new Enemy2(Movement.matrixInPixelX(i),Movement.matrixInPixelY(j));
						nemico2.isAlive = true;
						EnemySpawn2 = true;
						System.out.println("Spawno nemico2");
					}
					if (nemico2.getLife()>0)
						canvas.getGraphicsContext2D().drawImage(GraphicEnemy.getImg(), nemico2.getX(), nemico2.getY(), Enemy.getSize()+20, Enemy.getSize()+20);
					else {

						nemico2.isAlive = false;
					}
					
					//Vita upper enemy
					if (nemico2.getLife()>0) {
						canvas.getGraphicsContext2D().setFill(Color.BLACK);
						canvas.getGraphicsContext2D().fillRect(nemico2.getX(), nemico2.getY()-10,  200/3, 5); //200 Life Max enemy
								
						if (nemico2.getLife()>132)
							canvas.getGraphicsContext2D().setFill(Color.LIME);
						if (nemico2.getLife()<132)
							canvas.getGraphicsContext2D().setFill(Color.YELLOW);
						if (nemico2.getLife()<66)
							canvas.getGraphicsContext2D().setFill(Color.RED);		
						
						canvas.getGraphicsContext2D().fillRect(nemico2.getX(), nemico2.getY()-10, nemico2.getLife()/3, 5); //Hero.getLife()*2 perchè barra lunga 200
					}
					//fine vita upper enemy
					break;	
				case Cell.ENEMYKEY:
					break;
				default:
					break;					
				}
			}
		}
		
		
		//Disegno HERO con la sua Pistola
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
		//Fine disegno Hero
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
/*
 * ANIMAZIONE SWITCH SCENE/DOOR
static Service<Void> service = new Service<Void>() { //animations
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				if (transition) {
					int value = 0;
					while(value<= (Settings.x+100)) {
						canvas.getGraphicsContext2D().setFill(Color.BLACK);
						canvas.getGraphicsContext2D().fillOval(Settings.x/2, Settings.y/2, value, value);
						canvas.getGraphicsContext2D().fillOval(Settings.x/2-value, Settings.y/2, value, value);
						canvas.getGraphicsContext2D().fillOval(Settings.x/2, Settings.y/2-value, value, value);
						canvas.getGraphicsContext2D().fillOval(Settings.x/2-value, Settings.y/2-value, value, value);
						//per togliere il cerchio fai un clean e disegni uno piu piccolo...
						value += 1;
						Thread.sleep(1);
						System.out.println(value);
					}
					
					System.out.println("finito disegno transition");
					transition = false;
					service.cancel();
				}
				return null;
			} 
		};
		
	}
		
};*/