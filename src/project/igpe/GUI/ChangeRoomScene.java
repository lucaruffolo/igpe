package project.igpe.GUI;

import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.Effects;
import project.igpe.classes.GraphicHero;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class ChangeRoomScene {
	
	private static Image imgBlackScreen = new Image(ChangeRoomScene.class.getResourceAsStream(".."+File.separator+"images" + File.separator + "blackscreen.jpg"));

    @FXML
    private static ImageView BlackScreen = new ImageView(imgBlackScreen);

    
    
    public static void changeRoom () throws Exception {
    	Main.GameInPause = false;
    	Sound.musicPause();
    	String openDoor = "src/project/igpe/sounds/apertura_porta.wav";
		Effects.setEffects(openDoor);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();
		while(Effects.audioclip.isPlaying()) {
		}
		//ChangeRoomScene.BlackScreen.setImage(ChangeRoomScene.imgBlackScreen);
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("ChangeRoom.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene changeRoom = new Scene(root, 1270,899); 
		Main.window.setScene(changeRoom);
		String ladderEffect = "src/project/igpe/sounds/cambio_stanza.wav";
		Effects.setEffects(ladderEffect);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();
		while(Effects.audioclip.isPlaying()) {
		}
		Main.resumeGame();
		Sound.musicStart();
    }

    
    
    
	public ImageView getBlackScreen() {
		return BlackScreen;
	}

	public void setBlackScreen(ImageView blackScreen) {
		BlackScreen = blackScreen;
	}
	
}