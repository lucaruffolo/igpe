package project.igpe.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import project.igpe.main.Main;

public class Options {

    @FXML
    private Slider sliderMusic;

    @FXML
    private CheckBox bttFullscreen;

    @FXML
    private Label lblEffects;

    @FXML
    private Slider sliderEffects;

    @FXML
    private AnchorPane MenuOptions;

    @FXML
    private Button bttApply;

    @FXML
    private Label lblMusic;

    @FXML
    private Button bttBack;

    @FXML
    void DragEffects(ActionEvent event) {

    }

    @FXML
    void DragMusic(ActionEvent event) {

    }

    @FXML
    void ClickFullscreen(ActionEvent event) {

    }

    @FXML
    void ClickBack(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader(Options.class.getResource("Opzioni.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		Main.window.setScene(menuIniziale);
    }

    @FXML
    void ClickApply(ActionEvent event) {

    }

}