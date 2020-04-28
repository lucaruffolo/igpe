package project.igpe.classes;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GraphicsGame extends StackPane{
	
	private Canvas canvas;
	
	private Movement movimento;
	
	public GraphicsGame(Movement movimento) {
		this.movimento = movimento;
		canvas = new Canvas();
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new MovementControl(movimento, this));
		getChildren().add(canvas);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());        
	}
	
	public void draw() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(int i = 0; i < movimento.getFirstmap().getCella().length; i++) {
			int x = i * Settings.block;
			for(int j = 0; j < movimento.getFirstmap().getCella()[i].length; j++) {
				int y = j * Settings.block;
				switch(movimento.getFirstmap().getCella()[i][j].getType()) {
//					case Cell.HERO:
//						canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), x, y, Settings.block,Settings.block);
//						break;
					case Cell.WALL:
						canvas.getGraphicsContext2D().setFill(Color.BLUE);
						canvas.getGraphicsContext2D().fillRect(x+Settings.block/4, y, Settings.block*0.5, Settings.block*0.9);						
						break;
					default:
						break;
				}
				if(movimento.getPg().getX()==i && movimento.getPg().getY()==j)
					canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), x, y, Settings.block,Settings.block);
			}			
		}
	}
}
