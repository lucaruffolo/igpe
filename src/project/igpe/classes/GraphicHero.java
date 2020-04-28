package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class GraphicHero {

	//the current image to draw
		private static Image[] images = new Image[] {
			new Image(GraphicHero.class.getResourceAsStream("images" + File.separator + "pacmandx.png")),
			new Image(GraphicHero.class.getResourceAsStream("images" + File.separator + "pacmansx.png")),
			new Image(GraphicHero.class.getResourceAsStream("images" + File.separator + "pacmangiu.png")),
			new Image(GraphicHero.class.getResourceAsStream("images" + File.separator + "pacmansu.png"))
		};
		private static Image img = images[0];	
		
		public static Image getImg() {
			return img;
		}
		
		public static void updateDirection(int direction) {
			if(direction < images.length)
				img = images[direction];
		}
	
}
