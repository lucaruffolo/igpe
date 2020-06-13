package project.igpe.classes;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import project.igpe.GUI.MenuIniziale;
import project.igpe.main.Main;


public class MovementControl implements EventHandler<KeyEvent> {

	private Movement movimento;
	private static Scene sceneGame;
	private static Scene ripristinoGame;
	
	public MovementControl(Movement movimento) {
		this.movimento = movimento;		
	}



	@Override
	public void handle(KeyEvent e) {
		if (!Main.GameInPause) {
			switch (e.getCode()) {
			case LEFT:
				movimento.move(Movement.MOVE_LEFT);
				break;
			case RIGHT:
				movimento.move(Movement.MOVE_RIGHT);
				break;
			case UP:
				movimento.move(Movement.MOVE_UP);
				break;
			case DOWN:
				movimento.move(Movement.MOVE_DOWN);
				break;
			case X:
				Hero.setLife(100);			
			case L:
				if (Hero.getLife()>0)
					Hero.setLife(Hero.getLife()-10);			
				break;	
			case C:
				System.out.println(Hero.getX()+" . "+ Hero.getY());
				
				break;
			case SPACE:
				Hero.shoot();
				break;
			case ESCAPE:
				ripristinoGame=Main.window.getScene();				
			 	Main.pauseGame();
		    	Sound.musicPause();
		    	String openDoor = "src/project/igpe/sounds/menupausa.wav";
				Effects.setEffects(openDoor);
				Effects.modifyVolumeEffetcs(0.05);
				Effects.EffectsStart();
				
				FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  
				AnchorPane root = null;
				try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}
				Scene menuPausa = new Scene(root, 1270, 900);	
				Main.window.setScene(menuPausa);
				Sound.musicStart();	  
				break;					
			default:
				break;
			}
		}
	}

	public static Scene getSceneGame() {
		return sceneGame;
	}

	public static Scene getRipristinoGame() {
		return ripristinoGame;
	}

	public static void setRipristinoGame(Scene ripristinoGame) {
		MovementControl.ripristinoGame = ripristinoGame;
	}

	

}
