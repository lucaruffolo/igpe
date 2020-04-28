package project.igpe.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.igpe.GUI.MenuIniziale;
import project.igpe.GUI.SceneHandler;
import project.igpe.classes.GraphicsGame;
import project.igpe.classes.Hero;
import project.igpe.classes.Maps;
import project.igpe.classes.Movement;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia

		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		GraphicsGame game = new GraphicsGame(new Movement(new Hero(), new Maps()));
		Scene scene2 = new Scene(game, 1024,720);
		Scene scene = new Scene(root, 1024,720); 
		SceneHandler.init(scene);
		SceneHandler.add("MenuIniziale", root);
		SceneHandler.add("Gioco", game);
		primaryStage.setScene(scene2);
//		primaryStage.setTitle("Titolo di Prova");
//		primaryStage.setMinHeight(800);;
//		primaryStage.setMinWidth(800);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);	
	}

}