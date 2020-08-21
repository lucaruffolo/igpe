package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class Bullet {
	
	public static final int maxAmmo = 3;

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
	private int speed=10;
	
	public boolean alive;
	public static int heroAmmo = 0;
	
	
	public Bullet(int posX, int posY, int dir) {
		super();
		this.setPosX(posX);
		this.setPosY(posY);
		this.dir = Hero.getDirHero();
		alive=true;
		
	}


	public void moveBullet() {
		
			if(Movement.MOVE_RIGHT==dir) {
				if (collisionBullet(getPosX(), getPosY()))
					setPosX(getPosX() + speed);
				else
					alive = false;
			}
			if(Movement.MOVE_DOWN==dir) {
				if (collisionBullet(getPosX(), getPosY()))
					setPosY(getPosY() + speed);
				else
					alive = false;
			}
			if(Movement.MOVE_LEFT==dir) {
				if (collisionBullet(getPosX(), getPosY()))
					setPosX(getPosX() - speed);
				else
					alive = false;
			}
			if(Movement.MOVE_UP==dir) {
				if (collisionBullet(getPosX(), getPosY()))
					setPosY(getPosY() - speed);
				else
					alive = false;
			}
	}

	
	public static boolean collisionBullet(int x, int y) {
		if (Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.EMPTY 
				|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.FALLINGDOWN
					|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.OBSTACLEDAMAGE)
			return true;
		
		return false;		
	}

	

	public static void setDirBullet(int dir) {
		imgBullet = imagesBullet[dir];
	}


	public Image getImgBullet() {
		return imgBullet;
	}

	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}



}
