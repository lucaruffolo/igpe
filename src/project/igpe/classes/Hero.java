package project.igpe.classes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Hero {
	public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private static String name;
	private static Boolean sex; //0 maschio | 1 femmina
	private static double life=100;
	public static int speed=3;
	private static int size=60;
	private Image img;
	private static int x;
	private static int y;
	public static int velX = 0;
	public static int velY = 0;
	public static int dirHero = 3;
	private static List<Bullet> contenitoreBullets;
	public static int counterShoot = 0;
	public static int counterKill = 0;

	public static boolean lockRight = false;
	public static boolean lockLeft = false;
	public static boolean lockUp = false;
	public static boolean lockDown = false;
	public static long TimeStartPlayed = 0;
	public static long TimeEndPlayed = 0;

	public static boolean takePistol = false;
	public static boolean takeKey = false;
	public static boolean morto = false;

	public Hero() {
		name = "nome";
		sex = false;
		ResetPosition();
		contenitoreBullets=new ArrayList<Bullet>();
		
	}
	
	
	
	public static void moveHero() {
	
		if (!Movement.collisionWall(x, y) && !Movement.collisionObstacle(x, y)){
			x += velX;
			y += velY;
			
		} else if (getDirHero() == MOVE_RIGHT){			
			
			lockRight = true;
			if (!lockDown && !lockLeft && !lockUp) {				
				x -= velX;
				y -= velY;
			}			
			velX = 0;
			velY = 0;						
		}	else if (getDirHero() == MOVE_LEFT){
			
			lockLeft = true;
			if (!lockDown && !lockRight && !lockUp){
				x -= velX;
				y-=velY;
			}		
			velX = 0;
			velY = 0;
			
		}	else if (getDirHero() == MOVE_UP){
			
			lockUp = true;
			if (!lockDown && !lockLeft && !lockRight){
				x -= velX;
				y-=velY;
			}			
			velX = 0;
			velY = 0;
			
		}	else if (getDirHero() == MOVE_DOWN){
			
			lockDown = true;
			if (!lockRight && !lockLeft && !lockUp){
				x -= velX;
				y-=velY;
			}			
			velY = 0;
			velX = 0;
		}
		
		Movement.checkHero(x, y); //checkDoor(x, y);collisionDamage(x, y);
		checkTakeKey();
			
	}
	
	public static void checkTakeKey() {
		int x = 10;
		int y = 10;
		if (Maps.getIndiceMappe() == 23 && GraphicsGame.EnemySpawn && GraphicsGame.nemico.isAlive == false) {
			if (Movement.room.getCellType(18, 7) == Cell.WALL)
				Movement.room.setCellType(18, 7,Cell.EMPTY);
		}
		if (Maps.getIndiceMappe() == 24 && GraphicsGame.EnemySpawn && GraphicsGame.nemico.isAlive == false) {
			if (Movement.room.getCellType(x, y) == Cell.WALL)
				Movement.room.setCellType(x, y,Cell.EMPTY);
		}
		if (Maps.getIndiceMappe() == 25 && GraphicsGame.EnemySpawn && GraphicsGame.nemico.isAlive == false) {
			if (Movement.room.getCellType(x, y) == Cell.WALL)
				Movement.room.setCellType(x, y,Cell.EMPTY);
		}
		if (Maps.getIndiceMappe() == 26 && GraphicsGame.EnemySpawn && GraphicsGame.nemico.isAlive == false) {
			if (Movement.room.getCellType(x, y) == Cell.WALL)
				Movement.room.setCellType(x, y,Cell.EMPTY);
		}
	}
	
	public static void resetHeroLockMove() {
		Hero.lockRight = false;
		Hero.lockLeft = false;
		Hero.lockUp = false;
		Hero.lockDown = false;
	}
	
	public static int getDirHero() {
		return dirHero;
	}

	public static void setDirHero(int dirHero) {
		Hero.dirHero = dirHero;
	}
	
	public static int getVelX() {
		return velX;
	}

	static public void setVelX(int velX) {
		Hero.velX = velX;
	}

	public static int getVelY() {
		return velY;
	}

	public static void setVelY(int velY) {
		Hero.velY = velY;
	}

	public static void resetHero () throws Exception {
		life = 100;
		x=Settings.x/2;
		y=Settings.y/2;
		counterShoot = 0;
		counterKill = 0;
		takePistol = false;
	}
	
	public static void ResetPosition () {
		x=Settings.x/2;
		y=Settings.y/2;
	}
	
	public static void shoot() {
		int x = 0;
		int y = 0;
		
		if (Hero.getDirHero() == Hero.MOVE_LEFT) {
			x = Hero.getX() - 50;
			y = Hero.getY() - 5;
		}
		if (Hero.getDirHero() == Hero.MOVE_RIGHT){
			x = Hero.getX() + 50;
			y = Hero.getY() - 5;
		}
		if (Hero.getDirHero() == Hero.MOVE_UP){
			x = Hero.getX() - 2;
			y = Hero.getY() - 60;
		}
		if (Hero.getDirHero() == Hero.MOVE_DOWN){
			x = Hero.getX() + 5;
			y = Hero.getY() + 60;
		}


		Bullet bullet =  new Bullet(x, y, Movement.getDir());
		counterShoot++;
		contenitoreBullets.add(bullet);
	}


	public static List<Bullet> getContenitoreBullets() {
		return contenitoreBullets;
	}

	public static void setContenitoreBullets(List<Bullet> bullets) {
		Hero.contenitoreBullets = bullets;
	}

	
	
	public static String getName() {
		return name;
	}

	public void setName(String nome) {
		Hero.name = nome;
	}

	public static Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sesso) {
		Hero.sex = sesso;
	}

	public static double getLife() {
		return life;
	}

	public static void setLife(double d) {
		life = d;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speedx) {
		speed = speedx;
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int sizex) {
		size = sizex;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int xx) {
		x = xx;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int yy) {
		y = yy;
	}

	public static void clearAmmo() {
		contenitoreBullets.clear();
	}



	public static boolean isTakeKey() {
		return takeKey;
	}



	public static void setTakeKey(boolean takeKey) {
		Hero.takeKey = takeKey;
	}



	public static boolean isMorto() {
		return morto;
	}



	public static void setMorto(boolean morto) {
		Hero.morto = morto;
	}
	
	

	
}
