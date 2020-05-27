package project.igpe.classes;

public class Cell {

	public static final int EMPTY = 0;
	public static final int HERO = 1;
	public static final int POINT = 2;
	public static final int WALL = 3;
	public static final int ENEMY = 4;
	public static final int DOOR = 5;
	public static final int OBSTACLE = 6;
	public static final int OBSTACLEDAMAGE = 8;
	public static final int FALLINGDOWN = 9;
	
	private int type;
	
	public Cell(int type) {
		this.type = type;
	}
	
	
	
	
	public void setType(int type) {
		this.type = type;
	}
	 
	public int getType() {
		return type;
	}
}
