package project.igpe.classes;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import project.igpe.GUI.MenuIniziale;
import project.igpe.GUI.SceneHandler;
import project.igpe.GUI.SleepThread;
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
		case X:
			Hero.setLife(100);			
			graphics.draw();
		case L:
			if (Hero.getLife()>0)
				Hero.setLife(Hero.getLife()-10);			
			graphics.draw();
			break;
			
		case ESCAPE:
			
			FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  
			AnchorPane root = null;
			try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}			
			Scene menuPausa = new Scene(root, 1270, 900);	

			try {	SceneHandler.SleepScene(menuPausa);	} catch (Exception e2) {e2.printStackTrace();	}

			
			/*
			SleepThread t1 = new SleepThread();
			Thread t = new Thread(t1);
			t.start();
			*/
				
			//Main.window.setScene(menuPausa);
			
			break;
		case P:
				
			break;
		default:
			break;
		}
	}

	public static Scene getSceneGame() {
		return sceneGame;
	}
	

}
