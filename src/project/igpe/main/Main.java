package project.igpe.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.igpe.GUI.MenuIniziale;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene scene = new Scene(root, 1024,720); 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Test Name");
		primaryStage.setMinHeight(800);;
		primaryStage.setMinWidth(800);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);	
	}

}