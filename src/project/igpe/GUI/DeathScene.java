package project.igpe.GUI;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.Effects;
import project.igpe.classes.Hero;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class DeathScene {

	@FXML
    private ImageView BlackScreen;

	public static void Rip () throws Exception {
		
    	Main.pauseGame();
    	Sound.musicPause();
    
    	FXMLLoader loader = new FXMLLoader(DeathScene.class.getResource("DeathScene.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene changeRoom = new Scene(root, 1270,899); 
		Main.window.setScene(changeRoom);
		String ladderEffect = "src/project/igpe/sounds/death.wav"; //cambiare con musica morte
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