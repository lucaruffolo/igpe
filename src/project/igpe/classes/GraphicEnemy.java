package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class GraphicEnemy {

	private static Image[] images = new Image[] {		
			
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "enemy" + File.separator + "enemyDx.gif")), //dx
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "enemy" + File.separator + "enemySx.gif")), //sx
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "enemy" + File.separator + "enemyUp.png")), //up
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "enemy" + File.separator + "enemyDown.png")), //down
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "enemy" + File.separator + "enemyDown.png")) //ferma down
		};
	private static Image img = images[4];
	
	
	public static Image getImg() {
		return img;
	}
	
	public static void setImgDir(int dir) {
		img=images[dir];			
	}
}
