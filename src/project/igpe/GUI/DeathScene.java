package project.igpe.GUI;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import project.igpe.classes.Effects;
import project.igpe.classes.Hero;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class DeathScene {

    @FXML
    private ImageView BlackScreen;

    @FXML
    public static Label DeathName;
    
   
    public static void setDeathName() {
    	// Da Verificare se vogliamo mettere il nome da un bug non si cambia la label!  
    	
		if (Hero.getSex().equals(true)) {			
			DeathName.setTextFill(Color.PINK);
		//	DeathName.setText(Hero.getName());
		} else {
			DeathName.setTextFill(Color.BLUE);			
			//DeathName.setText(Hero.getName());  
		}		
	} 

	public static void Rip () throws Exception {
    	Main.pauseGame();
    	Sound.musicPause();
    	String openDoor = "src/project/igpe/sounds/apertura_porta.wav";
		Effects.setEffects(openDoor);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();
		
		while(Effects.audioclip.isPlaying()) {
			
		}
		
    	FXMLLoader loader = new FXMLLoader(DeathScene.class.getResource("DeathScene.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene changeRoom = new Scene(root, 1270,899); 
		Main.window.setScene(changeRoom);
		String ladderEffect = "src/project/igpe/sounds/cambio_stanza.wav"; //cambiare con musica morte
		Effects.setEffects(ladderEffect);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();
		
		while(Effects.audioclip.isPlaying()) {
			
		}
		
		FXMLLoader loaderMenu = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane rootMenu = (AnchorPane) loaderMenu.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(rootMenu, 1024,720); 
		Main.window.setScene(menuIniziale);		
		Main.window.centerOnScreen();
		//Main.resumeGame();
		Sound.musicStart();
		
		Hero.resetHero();
    }
    
		
}