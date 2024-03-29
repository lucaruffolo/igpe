package project.igpe.classes;

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
	public boolean isAlive = false;
	private static int size = 50;
	public static Boolean keyDrop = false; 


	public Enemy(int xx, int yy) {
		x = xx;
		y = yy;
		life = 200;
	} 
	
	public static int nRandDir = (int) (4.0 * Math.random());
	
	public static void moveEnemy() {
		if (GraphicsGame.EnemySpawn) {
	    moving(nRandDir);
	    GraphicEnemy.setImgDir(dirEnemy);
	    if (GraphicsGame.nemico.getLife()<=0) {
	    	GraphicsGame.nemico.isAlive = false;
	    	}
		}
	}

	public static int contadir = 0;	
	public static boolean stopMoving = false;
	
	public static void resetEnemy() {
		GraphicsGame.EnemySpawn=false;
		BulletEnemy.setAlive(false);
		
	}
	
	public static void moving (int dir) {
		
		if (GraphicsGame.nemico.isAlive) {
			
			dirEnemy = dir;
			
			if (!Movement.collisionFall(x, y) && !Movement.collisionWall(x, y) && !Movement.collisionDoor(x, y) && !Movement.collisionObstacle(x, y) && !Movement.collisionObstacleDmg(x, y)){

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
				dirEnemy = nRandDir;
					
				}
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
						shoot();
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
	public static boolean colpoPartito = false;

	public static BulletEnemy bullet;
	
	public static void shoot() {	
		
		int xf = 0;
		int yf = 0;
		if (dirEnemy == Enemy.MOVE_LEFT) {
			xf = x - 50;
			yf = y - 5;
		}
		if (dirEnemy == Enemy.MOVE_RIGHT){
			xf = x + 50;
			yf = y - 5;
		}
		if (dirEnemy == Enemy.MOVE_UP){
			xf = x - 2;
			yf = y - 60;
		}
		if (dirEnemy == Enemy.MOVE_DOWN){
			xf = x + 5;
			yf = y + 60;
		}
		if (!colpoPartito && GraphicsGame.nemico.isAlive) {
			
			bullet =  new BulletEnemy(xf, yf, dirEnemy);
			colpoPartito = true;
		}
		
	}


	public int getX() {
		return x;
	}

	public static void setX(int xx) {
		x = xx;
	}

	public int getY() {
		return y;
	}

	public static void setY(int yy) {
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

	public static void dirEnemy(int dir) {
		dirEnemy = dir;
		
	}

	public static Boolean getKeyDrop() {
		return keyDrop;
	}

	public static void setKeyDrop(Boolean keyDrop) {
		Enemy.keyDrop = keyDrop;
	}


	


		
}
