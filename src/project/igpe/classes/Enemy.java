package project.igpe.classes;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Enemy {
	
	public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	
	private static int x;
	private static int y;
	private static int speed = 1;
	private int life = 200;
	public static int velX = 0;
	public static int velY = 0;
	public static int dirEnemy = 3;
	public final static int damage = 5;
	
	private static int size = 70;	
	private static List<Bullet> contenitoreBullets;
	
	
	public Enemy(int xx, int yy) {
		x = xx;
		y = yy;
		life = 200;
		contenitoreBullets = new ArrayList<Bullet>();
	} 
	
	public static int nRandDir = (int) (4.0 * Math.random());
	
	public static void moveEnemy() {

	    moving(nRandDir);
		/*		
		if (Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixX(y)) == Cell.EMPTY) {
			Movement.room.setCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixX(y), Cell.ENEMY);
		}
		*/
	}

	public static int contadir = 0;
	
	public static boolean stopMoving = false;
	public static void moving (int dir) {
		
		dirEnemy = dir;
		if (!Movement.collisionWall(x, y) && !Movement.collisionDoor(x, y) && !Movement.collisionObstacle(x, y) && !Movement.collisionHero(x, y)){

			if (dir == MOVE_RIGHT && !stopMoving)
				x += speed;		
			if (dir == MOVE_LEFT && !stopMoving)
				x -= speed;				
			if (dir == MOVE_UP && !stopMoving)
				y -= speed;		
			if (dir == MOVE_DOWN && !stopMoving) 
				y += speed;	
			if(x==Settings.x/4 || y==Settings.y/4 || x==Settings.x/2 || y==Settings.y/2) {
				y += speed;
				x += speed;
				service.restart();
			}
		} else {			
			
			int direzione = nRandDir;		
			while(nRandDir == direzione) {
				nRandDir = (int) (4.0 * Math.random());				
			}
			if (dir == MOVE_RIGHT) 
				x -= speed*3;			
			if (dir == MOVE_LEFT) 
				x += speed*3;			
			if (dir == MOVE_UP) 
				y += speed*3;			
			if (dir == MOVE_DOWN) 
				y -= speed*3;
		
			service.restart();
			contadir++;
			System.out.println("dir "+ nRandDir+" cambio direzione ->" + contadir);
				
				
			}
		
	}



	static Service<Void> service = new Service<Void>() { //like timer
		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {				
				@Override
				protected Void call() throws Exception {

					int value = 0;					
					while(value <= 15) {
						
						value++;
						stopMoving=true;
						Thread.sleep(35);					
					}
					
					int direzione = nRandDir;		
					while(nRandDir == direzione) {
						nRandDir = (int) (4.0 * Math.random());				
					}
					
					stopMoving = false;	
					
				return null;
				} 
			};
		}
	};

	
	public static List<Bullet> getContenitoreBullets() {
		return contenitoreBullets;
	}
	
	public static void setContenitoreBullets(List<Bullet> contenitoreBullets) {
		Enemy.contenitoreBullets = contenitoreBullets;
	}

	public int getX() {
		return x;
	}

	public void setX(int xx) {
		x = xx;
	}

	public int getY() {
		return y;
	}

	public void setY(int yy) {
		y = yy;
	}

	public double getSpeed() {
		return speed;
	}


	public static int getSize() {
		return size;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}



		
}
