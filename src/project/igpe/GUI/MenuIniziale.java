package project.igpe.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import project.igpe.classes.GraphicsGame;
import project.igpe.classes.Hero;
import project.igpe.classes.Maps;
import project.igpe.classes.Movement;
import project.igpe.main.Main;

public class MenuIniziale {

	    @FXML
	    private Button Credits;

	    @FXML
	    private Button Start;

	    @FXML
	    private Label Title;

	    @FXML
	    private Button Option;

	    @FXML
	    private Button Exit;


	    @FXML
	    void ClickStart(ActionEvent event) {
	    	//Genera il GAME quando si clicca sul bottone e switcha da menu -> game
			GraphicsGame game = new GraphicsGame(new Movement(new Hero(), new Maps()));
			Scene scenegame = new Scene(game, 1280,960);
			Main.window.setScene(scenegame);
			game.draw();
			

	    }

	

}

    

