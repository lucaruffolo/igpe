package project.igpe.GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.igpe.classes.Settings;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class Options {
	
	@FXML
    private Label numberEffects;
	
	@FXML
    private Label numberMusic;

	@FXML
    private Slider sliderMusic;

    @FXML
    private Label lblEffects;

    @FXML
    private Slider sliderEffects;

    @FXML
    private AnchorPane MenuOptions;

    @FXML
    private Label lblMusic;

    @FXML
    private Button bttBack;
        
    @FXML
    public void initialize() {
    	//save per gli effects
    	sliderEffects.setValue(Settings.AudioEffects);
    	Double valore = sliderEffects.getValue();
    	int valoreprecon = valore.intValue();
    	String valoreconvertito = String.valueOf(valoreprecon);
    	numberEffects.setText(valoreconvertito);
    	
    	//save per la music
    	sliderMusic.setValue(Settings.AudioMusic);
    	Double valore2 = sliderMusic.getValue();
    	int valoreprecon2 = valore2.intValue();
    	String valoreconvertito2 = String.valueOf(valoreprecon2);
    	numberMusic.setText(valoreconvertito2);
    	double valoreVolume = valore2/1000;
    	Sound.modifyVolume(valoreVolume);
    	
    }
    
    @FXML
    void DragEffects(MouseEvent event) {
    	Double valore = sliderEffects.getValue();
    	int valoreprecon = valore.intValue();
    	String valoreconvertito = String.valueOf(valoreprecon);
    	numberEffects.setText(valoreconvertito);
    	Settings.AudioEffects = valore;
    }
    
    @FXML
    void ClickEffects(MouseEvent event) {
    	Double valore = sliderEffects.getValue();
    	int valoreprecon = valore.intValue();
    	String valoreconvertito = String.valueOf(valoreprecon);
    	numberEffects.setText(valoreconvertito);
    	Settings.AudioEffects = valore;
    }

    @FXML
    void DragMusic(MouseEvent event) {
    	Double valore = sliderMusic.getValue();
    	int valoreprecon = valore.intValue();
    	String valoreconvertito = String.valueOf(valoreprecon);
    	numberMusic.setText(valoreconvertito);
    	double valoreVolume = valore/1000;
    	Sound.modifyVolume(valoreVolume);
    	Settings.AudioMusic = valore;
    }
    
    @FXML
    void ClickMusic(MouseEvent event) {
    	Double valore = sliderMusic.getValue();
    	int valoreprecon = valore.intValue();
    	String valoreconvertito = String.valueOf(valoreprecon);
    	numberMusic.setText(valoreconvertito);
    	double valoreVolume = valore/1000;
    	Sound.modifyVolume(valoreVolume);
    	Settings.AudioMusic = valore;
    }
/*
    @FXML
    void ClickFullscreen(ActionEvent event) {
    	
    	if (bttFullscreen.isSelected()) {
    		Main.window.setFullScreen(true);
    	}
    	else {
    		Main.window.setFullScreen(false);
    	}
    			
    }*/

    @FXML
    void ClickBack(ActionEvent event) throws Exception {
    	
    	if (!Pausa.MenuPausaAttivo) {
	    	FXMLLoader loader = new FXMLLoader(Options.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che � legata all'interfaccia
			AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
			Scene menuIniziale = new Scene(root, 1024,720); 
			Main.window.setScene(menuIniziale);
			Main.window.centerOnScreen();
    	} else {
    		
    		FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  
			AnchorPane root = null;
			try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}
			Scene menuPausa = new Scene(root, 1270, 900);	
			Main.window.setScene(menuPausa);
			Main.window.centerOnScreen();
    	}
    }    

}