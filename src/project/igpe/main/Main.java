package project.igpe.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.igpe.GUI.MenuIniziale;
import project.igpe.GUI.SceltaDelPersonaggio;
import project.igpe.classes.GameLoop;
import project.igpe.classes.GraphicsGame;
import project.igpe.classes.Hero;
import project.igpe.classes.Maps;
import project.igpe.classes.Movement;
import project.igpe.classes.Sound;

public class Main extends Application{

	public static Stage window; //Schermate
	public static GraphicsGame game = new GraphicsGame(new Movement(SceltaDelPersonaggio.eroe, new Maps()));
	public static GameLoop gl=new GameLoop(game);
	public static Boolean GameInPause = false;
	private static Scene scenegame;	

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		window = primaryStage;
		FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		primaryStage.setScene(menuIniziale);
//		primaryStage.setResizable(false);
//		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		String musicFile = "src/project/igpe/sounds/menu.mp3";
		Sound.setMusic(musicFile);
		Sound.modifyVolume(0.05);
		Sound.musicLoop();
		Sound.musicStart();
	}
	

	public static void startGame() {	
			
		try { 	//Reset Mappe TXT + Background
			game.spawnBg();
		} catch (Exception e) {	e.printStackTrace();}
		
		
		if (!GameInPause) {
			scenegame = new Scene(game, 1270, 900);
		}
		
		Main.window.setScene(scenegame);
		Main.window.centerOnScreen();
		gl.start();
		GameInPause=false;
	}

	public static void pauseGame() {

	 	Hero.setVelX(0);
		Hero.setVelY(0);
		GameInPause = true;
		gl.stop();
	}
	
	public static void resumeGame() {
		GameInPause = false;
		gl.start();
	}
	
	public static void main(String[] args) {
		launch(args);	
	}


}