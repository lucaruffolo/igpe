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
	
	
	
	
	public Hero() {
		name = "nome";
		sex = false;
		x=6;
		y=6;
		size=50;
		
	}

	public static void shoot() {
		Bullet bullet =  new Bullet(x, y, Movement.getDir(), 10);
		Thread t = new Thread(bullet);
		t.start();
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
