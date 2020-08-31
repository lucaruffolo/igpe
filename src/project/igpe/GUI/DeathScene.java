package project.igpe.GUI;


import java.io.BufferedWriter;
import java.io.FileWriter;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.Effects;
import project.igpe.classes.Hero;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class DeathScene {


    @FXML
    private ImageView BlackScreen;

    @FXML
    private TextArea textAreaDetails;

    @FXML
    private Button bttDetails;

    @FXML
    private Button bttMenu;

 
	public static void Rip () throws Exception {
	
    	Main.pauseGame();
    	Sound.musicPause();
    
    	FXMLLoader loader = new FXMLLoader(DeathScene.class.getResource("DeathScene.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene changeRoom = new Scene(root, 1270,899); 
		Main.window.setScene(changeRoom);
		String ripSong = "src/project/igpe/sounds/death.wav"; //cambiare con musica morte
		Effects.setEffects(ripSong);
		Effects.modifyVolumeEffetcs(0.05);
		Effects.EffectsStart();	
		Hero.TimeEndPlayed = System.currentTimeMillis();   
		long s = Hero.TimeEndPlayed-Hero.TimeStartPlayed;
    	int z = (int) (s/1000);
    	z = z/60;
    	WriteScoreBoard(z);

    }
    private static void WriteScoreBoard(int time) throws Exception {
    	BufferedWriter writer = new BufferedWriter(new FileWriter("src/project/igpe/GUI/scoreboard.txt", true));
		writer.append(System.lineSeparator() + Hero.getName() + ";" + Hero.getSex() + ";" + Hero.counterKill + ";" + Hero.counterShoot + ";" +  time);
		writer.close();
	}
    @FXML
    void ClickBttMenu(ActionEvent event) throws Exception {
    	
    	Effects.EffectsStop();
		Sound.musicStart();		
		Hero.resetHero();
		
    	FXMLLoader loaderMenu = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane rootMenu = (AnchorPane) loaderMenu.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(rootMenu, 1024,720); 
		Main.window.setScene(menuIniziale);		
		Main.window.centerOnScreen();		
    }

    @FXML
    void CickBttDetails(ActionEvent event) {
    	String sesso;
    	if (!Hero.getSex())
    		sesso = "Maschio";
    	else
    		sesso = "Femmina";    	
    	
    	long s = Hero.TimeEndPlayed-Hero.TimeStartPlayed;
    	int z = (int) (s/1000);
    	String tempo;
    	if (z > 60) {
    		z = z/60;
    		if (z == 1)
    			tempo = " minuto";
    		else
    			tempo = " minuti";
    	}
    	else {
    		tempo = " secondi";
    	}
    		
    	
    	textAreaDetails.setText("Nome: " + Hero.getName() + "\n" +
    							"Sesso: " + sesso + "\n" +
    							"Nemici uccisi: " + Hero.counterKill + "\n" +
    							"Colpi Sparati: "+ Hero.counterShoot + "\n" +
    							"Tempo di gioco: " + z + tempo);
    	Thread t = new Thread(task);
    	t.setDaemon(true);
    	t.start();    	
    }

    		
    Task<Void> task = new Task<Void>() {    	
		@Override
		protected Void call() throws Exception {
			double value = 0.005;
			while(textAreaDetails.getOpacity() <= 1) {
				textAreaDetails.setOpacity(value);
				value += 0.005;
				Thread.sleep(55);
			}
			return null;
		}
		
    };    
		
}