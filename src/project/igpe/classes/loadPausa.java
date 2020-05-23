package project.igpe.classes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import project.igpe.GUI.MenuIniziale;
import project.igpe.main.Main;

public class loadPausa {


	public static void load() {
		MovementControl.sa.stop();
		FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  
		AnchorPane root = null;
		try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}
		Scene menuPausa = new Scene(root, 1270, 900);	
		Main.window.setScene(menuPausa);
		
	}
	
}
