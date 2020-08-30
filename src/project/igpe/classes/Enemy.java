package project.igpe.classes;

import java.util.ArrayList;
import java.util.List;

public class Enemy {
	
	public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private int x;
	private int y;
	private int speed = 2;
	private int life = 100;
	public static int velX = 0;
	public static int velY = 0;
	public static int dirHero = 3;
	
	private int size = 60;	
	private static List<Bullet> contenitoreBullets;
	
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		life = 100;
		contenitoreBullets = new ArrayList<Bullet>();
	} 
	
	public static void moveEnemy() {
		
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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSize() {
		return size;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}



		
}
