package project.igpe.classes;

import javafx.scene.image.Image;

public class Hero {
	private String name;
	private Boolean sex; //falso=maschio   true=femmina
	private static int life=100;
	private int speed=1;
	private int size;
	private Image img;
	private static int x;
	private static int y;
	
    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;

	
	public Hero() {
		name = "nome";
		sex = false;
		x=4;
		y=4;
		size=50;
		
	}
	

	public  String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public  Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sesso) {
		this.sex = sesso;
	}

	public static int getLife() {
		return life;
	}

	public static void setLife(int lifex) {
		life = lifex;
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

	public void setSize(int size) {
		this.size = size;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
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

	
	
	
}
