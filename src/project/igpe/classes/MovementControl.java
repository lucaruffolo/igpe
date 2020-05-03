package project.igpe.classes;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class MovementControl implements EventHandler<KeyEvent> {

	private Movement movimento;
	private GraphicsGame graphics;

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
		case ESCAPE:
			if (graphics.getScene().getWindow() instanceof Stage) {
				Stage stage = (Stage) graphics.getScene().getWindow();
				stage.close();
			}
			break;
		case P:
			Stage stage = (Stage) graphics.getScene().getWindow();
		
			System.out.println("Wait in corso");
			stage.showAndWait();
		
			break;
			
			
		default:
			break;
		}
	}

}
