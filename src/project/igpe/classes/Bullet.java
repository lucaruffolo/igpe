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
	public static int size = 20;
	public static int heroAmmo = 0;
	public static int damage = 10;
	
	public Bullet(int posX, int posY, int dir) {
		super();
		this.setPosX(posX);
		this.setPosY(posY);
		this.dir = Hero.getDirHero();
		alive=true;	
		
	}

	public void moveBullet() {
		
			if(Movement.MOVE_RIGHT==dir) {
				if (collisionBullet(getPosX(), getPosY())
						&& collisionBullet(getPosX(), getPosY()+size))					
					setPosX(getPosX() + speed);
				else
					alive = false;
			}
			if(Movement.MOVE_DOWN==dir) {
				if (collisionBullet(getPosX(), getPosY())
						&& collisionBullet(getPosX()+size, getPosY()))
					setPosY(getPosY() + speed);
				else
					alive = false;
			}
			if(Movement.MOVE_LEFT==dir) {
				if (collisionBullet(getPosX(), getPosY())
						&& collisionBullet(getPosX(), getPosY()+size))
					setPosX(getPosX() - speed);
				else
					alive = false;
			}
			if(Movement.MOVE_UP==dir) {
				if (collisionBullet(getPosX(), getPosY())
						&& collisionBullet(getPosX()+size, getPosY()))
					setPosY(getPosY() - speed);
				else
					alive = false;
			}
			
			//collisionW/Enemy
			if (GraphicsGame.nemico.isAlive) {
				
				if (getPosX()>= GraphicsGame.nemico.getX()-Enemy.getSize() && getPosX()<= GraphicsGame.nemico.getX()+Enemy.getSize() 
					&& getPosY()>= GraphicsGame.nemico.getY()-Enemy.getSize() && getPosY()<= GraphicsGame.nemico.getY()+Enemy.getSize()) {		
					
					GraphicsGame.nemico.setLife(GraphicsGame.nemico.getLife()-Bullet.damage);
					alive = false;
					if (GraphicsGame.nemico.getLife()<= 0) {
						Hero.counterKill++;					
					}
				}
				
			}
	}

	
	public static boolean collisionBullet(int x, int y) {
		if (Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.EMPTY 
				|| Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixY(y)) == Cell.FALLINGDOWN
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
