package project.igpe.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.GraphicsGame;
import project.igpe.classes.Hero;
import project.igpe.classes.Maps;
import project.igpe.classes.Movement;
import project.igpe.main.Main;

public class SceltaPersonaggio {

    @FXML
    private Label lblsdPersonaggio;

    @FXML
    private Label lblname;

    @FXML
    private TextField textName;

    @FXML
    private Button bttMale;

    @FXML
    private Button bttFemale;

    @FXML
    private Button bttGame;

    @FXML
    private Button bttBack;

    @FXML
    void clickBack(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che � legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		Main.window.setScene(menuIniziale);
		
    }

    @FXML
    void selectSEXMale(ActionEvent event) {

    }

    @FXML
    void selectSEXFemale(ActionEvent event) {

    }

    @FXML
    void clickGame(ActionEvent event) {
    	//Genera il GAME quando si clicca sul bottone e switcha da menu -> game
		GraphicsGame game = new GraphicsGame(new Movement(new Hero(), new Maps()));
		Scene scenegame = new Scene(game, 1280,960);
		Main.window.setScene(scenegame);
		game.draw();
    }

}

