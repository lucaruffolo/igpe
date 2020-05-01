package project.igpe.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
	    void ClickStart(ActionEvent event) throws Exception {
	    	
	    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("SceltaDelPersonaggio.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
			AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
			Scene menuSceltaPersonaggio = new Scene(root, 1024,720); 
			Main.window.setScene(menuSceltaPersonaggio);
			
	    }

	

}

    

