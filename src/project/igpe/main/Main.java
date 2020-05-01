package project.igpe.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.igpe.GUI.MenuIniziale;

public class Main extends Application{

	public static Stage window; //Schermate
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		window = primaryStage;
		FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		primaryStage.setScene(menuIniziale);
//		primaryStage.setResizable(false);
//		primaryStage.initStyle(StageStyle.UNDECORATED);		
		primaryStage.show();
	
	}
	

	public static void main(String[] args) {
		launch(args);	
	}

}