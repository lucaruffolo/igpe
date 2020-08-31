package project.igpe.classes;

import java.util.ArrayList;
import java.util.List;

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
	public static int nRand = (int) (4.0 * Math.random());
	
	public static void moveEnemy() {

	    moving(nRand);
		
		/*		
		if (Movement.room.getCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixX(y)) == Cell.EMPTY) {
			Movement.room.setCellType(Movement.pixelInMatrixX(x), Movement.pixelInMatrixX(y), Cell.ENEMY);
		}
		*/
	}
	

	public static void moving (int dir) {
		if (!Movement.collisionWall(x, y) && !Movement.collisionDoor(x, y) && !Movement.collisionObstacle(x, y) && !Movement.collisionHero(x, y)){
	
			if (dir == MOVE_RIGHT) {
				x += speed;
			}
			if (dir == MOVE_LEFT) {
				x -= speed;		
			}
			if (dir == MOVE_UP) {
				y -= speed;
			}
			if (dir == MOVE_DOWN) {
				y += speed;
			}
		}
		else {
			if (dir == MOVE_RIGHT) {
				x -= speed;
			}
			if (dir == MOVE_LEFT) {
				x += speed;
			}
			if (dir == MOVE_UP) {
				y += speed;
			}
			if (dir == MOVE_DOWN) {
				y -= speed;
				
			}
			int direzione = nRand;		
			while(nRand == direzione){				
				System.out.println(nRand);
				nRand = (int) (4.0 * Math.random());
			}			
						
		}
	}
	
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
