package project.igpe.GUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import project.igpe.main.Main;

public class Credits implements Initializable{

    @FXML
    private AnchorPane anchorPaneCredits;

    @FXML
    private MediaView media;
    
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
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File f=new File("src/project/igpe/videos/Editor.mp4");
		Media video=new Media(f.toURI().toString());
		MediaPlayer player =new MediaPlayer(video);
		media.setMediaPlayer(player);
		player.play();
	}
        
    
}