package project.igpe.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.GraphicHero;
import project.igpe.classes.GraphicsGame;
import project.igpe.classes.Hero;
import project.igpe.classes.Maps;
import project.igpe.classes.Movement;
import project.igpe.main.Main;

public class SceltaDelPersonaggio {
	
	private static Boolean sesso=false;
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
    private ImageView imgFemmina;
    
    @FXML
    private ImageView imgMaschio;
    

    
    @FXML
    void clickBack(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		Main.window.setScene(menuIniziale);
		
    }

    @FXML
    void selectSEXMale(ActionEvent event) {
    	
    	imgFemmina.setOpacity(0.20);
    	imgMaschio.setOpacity(1);
    	sesso = false;
    }

    @FXML
    void selectSEXFemale(ActionEvent event) {

    	imgMaschio.setOpacity(0.20);
    	imgFemmina.setOpacity(1);
    	sesso = true;
    }

    @FXML
    void clickGame(ActionEvent event) {    	
    	//stoppo la musica del menu
    	Sound.musicStop();
    	//Creo eroe ed imposto nome e sesso presi da utente
    	Hero eroe = new Hero();
    	eroe.setName(textName.getText());
    	eroe.setSex(sesso);
    	GraphicHero.selectSex(eroe.getSex());
    	//Disegno GIOCO
		GraphicsGame game = new GraphicsGame(new Movement(eroe, new Maps()));
		Scene scenegame = new Scene(game, 1270,960);
		Main.window.setScene(scenegame);
		Main.window.centerOnScreen();
		game.draw();
    }

}

