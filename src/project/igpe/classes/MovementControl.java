package project.igpe.classes;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import project.igpe.GUI.MenuIniziale;
import project.igpe.GUI.Pausa;
import project.igpe.GUI.WinScene;
import project.igpe.main.Main;


public class MovementControl implements EventHandler<KeyEvent> {

	private Movement movimento;
	private static Scene sceneGame;
	private static Scene ripristinoGame;
	
	
	public MovementControl(Movement movimento) {
		this.movimento = movimento;		
	}

	
	
	@Override
	public void handle(KeyEvent event) {
		
		if (!Main.GameInPause) {
			onKeyPressed(event);
			onKeyReleased(event);
		}		
	}
	
	public void onKeyReleased(KeyEvent e) {
		
		if (e.getEventType().equals(KeyEvent.KEY_RELEASED)) { 
			switch (e.getCode()) {
			case LEFT:
				Hero.setVelX(0);
				break;
			case RIGHT:
				Hero.setVelX(0);
				break;
			case UP:
				Hero.setVelY(0);
				break;
			case DOWN:
				Hero.setVelY(0);
				break;
			case SHIFT:
				Hero.setSpeed(2);
				break;
			default:
				break;
			}
		}	
	}
	
	public void onKeyPressed(KeyEvent e) {

		if(e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			switch (e.getCode()) {
			case N:
				for (int i = 0; i < Maps.getIndexYetChoosen().size(); i++) {
					System.out.println(Maps.getIndexYetChoosen());
				}
				break;
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
			case SHIFT:
				Hero.setSpeed(4);
				break;
			case I:
				try {
					WinScene.Win();
				} catch (Exception e2) {	e2.printStackTrace();}
				break;
			case R:
				Hero.ResetPosition();
				break;
			
			case C:
				System.out.println(" x= "+Hero.getX()+" . "+ " y= "+Hero.getY()+" CELL "+ Movement.room.getCellType(Movement.pixelInMatrixX(Hero.getX()),Movement.pixelInMatrixX(Hero.getY())));
				break;
			case M:
				System.out.println(Movement.pixelInMatrixX(Hero.getX())+" . "+ Movement.pixelInMatrixY(Hero.getY()));
				break;
			
			case SPACE:
				if (Hero.takePistol) {
					if (Bullet.heroAmmo < Bullet.maxAmmo) {
						Hero.shoot();
						Bullet.heroAmmo++;
						Effects.shootgun.setVolume(0.1);
						Effects.shootgun.play();
					}
				}
				break;
			case ESCAPE:
				ripristinoGame=Main.window.getScene();				
			 	Main.pauseGame();
			 	Pausa.MenuPausaAttivo = true;
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
				Main.window.centerOnScreen();
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
