package project.igpe.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.Maps;
import project.igpe.classes.MovementControl;
import project.igpe.main.Main;

public class Pausa {

    @FXML
    private Button bttMenuStart;

    @FXML
    private Button bttExit;

    @FXML
    private AnchorPane apBtt;

    @FXML
    private Button bttOptions;

    @FXML
    private AnchorPane apHome;

    @FXML
    private Button bttBackToGame;

    @FXML
    void ClickBackToGame(ActionEvent event) {
		Main.window.setScene(MovementControl.getRipristinoGame());
		Main.window.centerOnScreen();
		Main.resumeGame();
    }

    @FXML
    void ClickOptions(ActionEvent event) throws Exception {
    	//Sistemare Options copiare e modificare il RITORNA AL Menu di PAUSA
    	FXMLLoader loader = new FXMLLoader(Options.class.getResource("Options.fxml")); 
		AnchorPane root = (AnchorPane) loader.load(); 
		Scene menuImpostazioni = new Scene(root, 1024,720); 
		Main.window.setScene(menuImpostazioni);
		Main.window.centerOnScreen();
    }

    @FXML
    void ClickMenuStart(ActionEvent event) throws Exception {
    	Maps.setIndiceMappe(0);
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		Main.window.setScene(menuIniziale);
		Main.window.centerOnScreen();
    }

    @FXML
    void ClickExit(ActionEvent event) {
    	
    	Platform.exit();
    }

}
