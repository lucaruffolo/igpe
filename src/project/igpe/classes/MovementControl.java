package project.igpe.classes;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import project.igpe.main.Main;


public class MovementControl implements EventHandler<KeyEvent> {

	private Movement movimento;
	private static Scene sceneGame;
	private static Scene ripristinoGame;	
	public static SwitchAnimation sa;
	
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
			case SPACE:
				Hero.shoot();
				break;
				
			case ESCAPE:
				
				setRipristinoGame(Main.window.getScene());
				GraphicsGame.setTransition(true);	
				
				sa = new SwitchAnimation(Main.window.getScene());
				sa.start();
				
				
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
