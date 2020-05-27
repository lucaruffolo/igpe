package project.igpe.classes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Hero {
	private String name;
	private Boolean sex;
	private static int life=100;
	private static int speed=1;
	private static int size=60;
	private Image img;
	private static int x;
	private static int y;
	private static List<Bullet> contenitoreBullets;
	
	
	public static List<Bullet> getContenitoreBullets() {
		return contenitoreBullets;
	}

	public static void setContenitoreBullets(List<Bullet> bullets) {
		Hero.contenitoreBullets = bullets;
	}

	public Hero() {
		name = "nome";
		sex = false;
		x=6;
		y=6;
		contenitoreBullets=new ArrayList<Bullet>();
	}

	
	public static void shoot() {
		Bullet bullet =  new Bullet(x, y, Movement.getDir(), 10);
		contenitoreBullets.add(bullet);
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

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speedx) {
		speed = speedx;
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int sizex) {
		size = sizex;
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
