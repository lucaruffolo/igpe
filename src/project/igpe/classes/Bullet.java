package project.igpe.classes;

import javafx.scene.image.Image;

public class Bullet implements Runnable{
	
	private Image imgBulletDX = new Image("src/project/igpe/images/bullet.jpg");
	
	int posX;
	int posY;
	
	int dir;
	int speed=100;
	int frequenzy;
	
	boolean alive;
	
	
	public Bullet(int posX, int posY, int dir, int frequenzy) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.frequenzy = frequenzy;
		alive=true;
	}



	@Override
	public void run() {
		while(alive) {
			
			if(Movement.MOVE_RIGHT==dir) {
				posX+=speed;
			}
			if(Movement.MOVE_DOWN==dir) {
				posY-=speed;
			}
			if(Movement.MOVE_LEFT==dir) {
				posX-=speed;
			}
			if(Movement.MOVE_UP==dir) {
				posY+=speed;
			}
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
	} 
	
	
	
}
