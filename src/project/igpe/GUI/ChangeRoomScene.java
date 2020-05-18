package project.igpe.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.Effects;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class ChangeRoomScene {
	
	//private Image imgBlackScreen = new Image("src/project/igpe/images/blackscreen.jpg");

  //  @FXML
  //  private ImageView BlackScreen = new ImageView(imgBlackScreen);
    @FXML
    private ImageView BlackScreen;

    
    
    public static void changeRoom () throws Exception {
    	Main.pauseGame();
    	Sound.musicPause();
    	String openDoor = "src/project/igpe/sounds/apertura_porta.wav";
		Effects.setEffects(openDoor);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();
		while(Effects.audioclip.isPlaying()) {
		}
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
		Main.startGame();
		Sound.musicStart();
    }

    
    /*
    
	public ImageView getBlackScreen() {
		return BlackScreen;
	}

	public void setBlackScreen(ImageView blackScreen) {
		BlackScreen = blackScreen;
	}

	public Image getImgBlackScreen() {
		return imgBlackScreen;
	}
	*/
}