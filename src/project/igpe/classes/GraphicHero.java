package project.igpe.classes;

import java.io.File;

import javafx.scene.image.Image;

public class GraphicHero {

	//the current image to draw
		
		private static Image[] images = new Image[] {		
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschiosu.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschiogiu.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschiodx.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschiosx.png"))
			
		};
			
		/*
		private static Image[] imagesMale = new Image[] {		
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png"))
			
		};
		
		private static Image[] imagesFemale = new Image[] {		
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
			new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png"))
				
		};
		*/
		
		
		//private static Image[] images;
		
		

		public static void selectSex(boolean sex) {
			if (sex)
				System.out.println("Femmina");
			else
				System.out.println("maschio");
		}
		
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
