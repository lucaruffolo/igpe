package project.igpe.classes;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public class Hero {
	private String name;
	private Boolean sex=false; //falso=maschio   true=femmina
	private int life;
	private int speed=1;
	private int size;
	private Image img;
	private int x;
	private int y;
	
    public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	private Image[] images = new Image[4];
	public Hero() {
		x=0;
		y=0;
		size=50;
	}
	
	public void move(int direction) {
		switch (direction) {
			case MOVE_RIGHT:
				x += speed;
				img = images[direction];
				break;
			case MOVE_LEFT:
				x -= speed;
				img = images[direction];
				break;
			case MOVE_UP:
				y -= speed;
				img = images[direction];
				break;
			case MOVE_DOWN:
				y += speed;
				img = images[direction];
				break;
			default:
				break;
		}		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}
	
	
	
	
}
