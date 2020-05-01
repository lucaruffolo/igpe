package project.igpe.GUI;

import java.awt.Button;
import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import project.igpe.main.Main;

public class Credits {

    @FXML
    private AnchorPane anchorPaneCredits;

    @FXML
    private Label lblcredits;

    @FXML
    private Button bttBack;

    @FXML
    void clickBack(ActionEvent event) throws Exception {
    	
    	
    	
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));
    	AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
    	Scene menuIniziale = new Scene(root, 1024,720); 
    	Main.window.setScene(menuIniziale);
    }
}