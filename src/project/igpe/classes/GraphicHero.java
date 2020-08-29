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
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "HeroMDx.gif")), //dx
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "HeroMSx.gif")), //sx
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "HeroMUp.png")), //up
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "HeroMDown.gif")), //down
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "maschio.png"))	 //ferma down
					};
				img = images[4];
			}
			else {
				//FEMMINA FRONTALE				
				images = new Image[] {		
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png")),
						new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "femmina.png"))						
					};
				img = images[4];
			}
			
		}
		
		private static Image[] imagesPistol = new Image[] {		
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "pistoldx.png")), //dx
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "pistolsx.png")), //sx
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "pistolup.png")), //up
				new Image(GraphicHero.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "hero" + File.separator + "pistoldown.png")), //down
			};
		private static Image imgPistol = imagesPistol[3];
		
		public static Image[] getImagesPistol() {
			return imagesPistol;
		}


		public static Image getImgPistol() {
			return imgPistol;
		}


		public static Image getImg() {
			return img;
		}
		
		public static void setImgDir(int dir) {
			img=images[dir];			
		}
		public static void setImgPistolDir(int dir) {
			imgPistol=imagesPistol[dir];			
		}
		public static void updateDirection(int direction) {
			if(direction < images.length)
				img = images[direction];
		}

}
