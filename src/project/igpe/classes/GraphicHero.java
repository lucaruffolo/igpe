package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class GraphicHero {
		
		private static Image[] images = new Image[4];
		private static Image img = images[0];
		

		public static void selectSex(boolean sex) {
			if (sex==false) {
				//MASCHIO
				images = new Image[] {		
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png"))						
					};
				img = images[0];
			}
			else {
				//FEMMINA FRONTALE				
				images = new Image[] {		
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png"))							
					};
				img = images[0];
			}
			
		}
		

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
