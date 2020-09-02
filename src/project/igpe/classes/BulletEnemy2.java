package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class BulletEnemy2 {
	
	private static Image[] imagesBullet = new Image[] {		
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "bulletdx.gif")), //dx
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "bulletsx.gif")), //sx
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "bulletup.gif")), //up
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "bulletdown.gif")), //down
		};
	
	private static Image imgBullet = imagesBullet[3];
	
	private int posX;
	private int posY;
	
	private int dir;
	private int speed=5;
	
	public static boolean alive = false;;
	public static int size = 20;
	public static int damage = 10;
	
	public BulletEnemy2(int posX, int posY, int dir) {
		super();
		this.setPosX(posX);
		this.setPosY(posY);
		this.dir = Enemy2.dirEnemy;
		setAlive(true);	
	}

	public void moveBullet() {
		
		setDirBullet(dir);
		if(Movement.MOVE_RIGHT==dir) {
			if (collisionBullet(getPosX(), getPosY())
					&& collisionBullet(getPosX(), getPosY()+size))					
				setPosX(getPosX() + speed);
			else {
				alive = false;
				Enemy2.colpoPartito = false;
			}
		}
		if(Movement.MOVE_DOWN==dir) {
			if (collisionBullet(getPosX(), getPosY())
					&& collisionBullet(getPosX()+size, getPosY()))
				setPosY(getPosY() + speed);
			else {
				alive = false;
				Enemy2.colpoPartito = false;
			}
		}
		if(Movement.MOVE_LEFT==dir) {
			if (collisionBullet(getPosX(), getPosY())
					&& collisionBullet(getPosX(), getPosY()+size))
				setPosX(getPosX() - speed);
			else {
				alive = false;
				Enemy2.colpoPartito = false;
			}
		}
		if(Movement.MOVE_UP==dir) {
			if (collisionBullet(getPosX(), getPosY())
					&& collisionBullet(getPosX()+size, getPosY()))
				setPosY(getPosY() - speed);
			else {
				alive = false;
				Enemy2.colpoPartito = false;
			}
		}
		
		//collisionW/Enemy
		if (Hero.getLife()>0 && GraphicsGame.nemico2.getLife()>0) {
			
			if (getPosX() >= Hero.getX()-Hero.getSize() && getPosX() <= Hero.getX()+Hero.getSize() 
				&& getPosY() >= Hero.getY()-Hero.getSize() && getPosY() <= Hero.getY()+Hero.getSize()) {		
				
				alive = false;
				Enemy2.colpoPartito = false;

				Hero.setLife(Hero.getLife()-5);
				
			}
		}
		
	}
	
	public static boolean collisionBullet(int x, int y) {
		if (Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.EMPTY 
				|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.FALLINGDOWN
					|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.OBSTACLEDAMAGE
						|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.PISTOL
							|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.HEART
								|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.ENEMY
									|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.ENEMY2)
			return true;		
		return false;		
	}	
	public static void setDirBullet(int dir) {
		imgBullet = imagesBullet[dir];
	}
	public static Image[] getImagesBullet() {
		return imagesBullet;
	}

	public static void setImagesBullet(Image[] imagesBullet) {
		BulletEnemy2.imagesBullet = imagesBullet;
	}

	public Image getImgBullet() {
		return imgBullet;
	}

	public static void setImgBullet(Image imgBullet) {
		BulletEnemy2.imgBullet = imgBullet;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posXz) {
		posX = posXz;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posYz) {
		posY = posYz;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public static boolean isAlive() {
		return alive;
	}

	public static void setAlive(boolean alive) {
		BulletEnemy2.alive = alive;
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int size) {
		BulletEnemy2.size = size;
	}

	public static int getDamage() {
		return damage;
	}

	public static void setDamage(int damage) {
		BulletEnemy2.damage = damage;
	}
	
}
