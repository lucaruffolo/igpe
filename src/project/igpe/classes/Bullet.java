package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class Bullet {
	
	private Image imgBulletDX = new Image(Bullet.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "bullettest.png"));
	
	private int posX;
	private int posY;
	
	private int dir;
	private int speed=30;
	
	public boolean alive;
	
	
	public Bullet(int posX, int posY, int dir) {
		super();
		this.setPosX(posX);
		this.setPosY(posY);
		this.dir = dir;
		alive=true;
		
	}


	public void moveBullet() {
			if(Movement.MOVE_RIGHT==dir) {
				if (getPosX()<=1054)
					setPosX(getPosX() + speed);
				else
					alive = false;
			}
			if(Movement.MOVE_DOWN==dir) {
				if (getPosY()<=696)
					setPosY(getPosY() + speed);
				else
					alive = false;
			}
			if(Movement.MOVE_LEFT==dir) {
				if (getPosX()>=155)
					setPosX(getPosX() - speed);
				else
					alive = false;
			}
			if(Movement.MOVE_UP==dir) {
				if (getPosY()>=150)
					setPosY(getPosY() - speed);
				else
					alive = false;
			}
			
			try {
				Thread.sleep(1200/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	
	
	public Image getImgBulletDX() {
		return imgBulletDX;
	}



	public void setImgBulletDX(Image imgBulletDX) {
		this.imgBulletDX = imgBulletDX;
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
