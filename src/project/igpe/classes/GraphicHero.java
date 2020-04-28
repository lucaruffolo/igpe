package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class GraphicHero {

	//the current image to draw
		private static Image[] images = new Image[] {
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "dx.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "sx.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "su.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "giu.png"))
		};
		private static Image img = images[0];	
		
		public static Image getImg() {
			return img;
		}
		
		public static void setImgDir(int dir) {
			img=images[dir];
		}
		
		public static void updateDirection(int direction) {
			if(direction < images.length)
				img = images[direction];
		}
	
}
