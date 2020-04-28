package project.igpe.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
	    	SceneHandler.setCurrent("Gioco");
	    }

	

}

    

