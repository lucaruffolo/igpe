package project.igpe.classes;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project.igpe.GUI.MenuIniziale;
import project.igpe.main.Main;


public class MovementControl implements EventHandler<KeyEvent> {

	private Movement movimento;
	private GraphicsGame graphics;
	private static Scene sceneGame;
	
	public MovementControl(Movement movimento, GraphicsGame graphics) {
		this.movimento = movimento;
		this.graphics = graphics;
	}

	@Override
	public void handle(KeyEvent e) {
		switch (e.getCode()) {
		case LEFT:
			movimento.move(Movement.MOVE_LEFT);
			graphics.draw();
			break;
		case RIGHT:
			movimento.move(Movement.MOVE_RIGHT);
			graphics.draw();
			break;
		case UP:
			movimento.move(Movement.MOVE_UP);
			graphics.draw();
			break;
		case DOWN:
			movimento.move(Movement.MOVE_DOWN);
			graphics.draw();
			break;
		case L:
			if (Hero.getLife()>0)
				Hero.setLife(Hero.getLife()-10);			
			graphics.draw();
			break;
			
		case ESCAPE:
			
			if (graphics.getScene().getWindow() instanceof Stage) {
			
				sceneGame = Main.window.getScene();
				FXMLLoader loaderSleep = new FXMLLoader(MenuIniziale.class.getResource("SleepScene.fxml"));
				AnchorPane rootSleep = null;
				try {rootSleep = (AnchorPane) loaderSleep.load();} catch (IOException e2) {e2.printStackTrace();	}
				Scene menuSleep = new Scene(rootSleep, 1270, 900);
				menuSleep.setFill(Color.BLACK);
				
				
			//	Main.window.setScene(menuSleep);
				Main.window.centerOnScreen();
				System.out.println("wait");
			
				
				// Switcho sul menu di PAUSA
				FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
				AnchorPane root = null;
				try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}			
				Scene menuPausa = new Scene(root, 1270, 900); 
				Main.window.setScene(menuPausa);
				Main.window.centerOnScreen();
				
			}
			break;
		case P:
			
	//		Stage stage = (Stage) graphics.getScene().getWindow();
			System.out.println("Wait in corso");
	//		stage.showAndWait();
			
			// Switcho sul menu di PAUSA
			FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
			AnchorPane root = null;
			try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}			
			Scene menuPausa = new Scene(root, 1024,720); 
			Main.window.setScene(menuPausa);
			Main.window.centerOnScreen();

			
			
			
			break;
		default:
			break;
		}
	}

	public static Scene getSceneGame() {
		return sceneGame;
	}


}
