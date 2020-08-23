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
import project.igpe.classes.Hero;
import project.igpe.classes.Maps;
import project.igpe.classes.Sound;
import project.igpe.main.Main;

public class SceltaDelPersonaggio {
	
	private static Boolean sesso=false;
	private static Boolean notChooseSex=false;
	private static Boolean notChooseNickname=false;

	public static Hero eroe = new Hero();
	
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
    private Label lblNotChooseSex;
    
    @FXML
    private Label lblNotChooseNickname;
    

    
    @FXML
    void clickBack(ActionEvent event) throws Exception {
    	
		notChooseSex=false;
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));  //prendiamo il file dalla classe che è legata all'interfaccia
		AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
		Scene menuIniziale = new Scene(root, 1024,720); 
		Main.window.setScene(menuIniziale);		
    }

    @FXML
    void selectSEXMale(ActionEvent event) {
    	
    	lblNotChooseSex.setOpacity(0);
    	notChooseSex=true;
    	imgFemmina.setOpacity(0.20);
    	imgMaschio.setOpacity(1);
    	sesso = false;
    }

    @FXML
    void selectSEXFemale(ActionEvent event) {
    	
    	lblNotChooseSex.setOpacity(0);
    	notChooseSex=true;
    	imgMaschio.setOpacity(0.20);
    	imgFemmina.setOpacity(1);
    	sesso = true;
    }

    @FXML
    void clickGame(ActionEvent event) throws Exception {    	
    
    	if (notChooseSex) 
    		lblNotChooseSex.setOpacity(0);    	
    	else
    		lblNotChooseSex.setOpacity(1); 
    	
    	if (!textName.getText().isEmpty()) {
    		notChooseNickname=true;
    		lblNotChooseNickname.setOpacity(0);
    	}
    	else
    		lblNotChooseNickname.setOpacity(1);

		if (notChooseSex && notChooseNickname)	{
						
			Sound.musicStop();
			String musicFile = "src/project/igpe/sounds/dead_song.mp3"; //musica iniziale ?
			Sound.setMusic(musicFile);
			Sound.modifyVolume(0.04);
			Sound.musicLoop();
			Sound.musicStart();		
		
			eroe.setName(textName.getText());
			eroe.setSex(sesso);
			GraphicHero.selectSex(Hero.getSex());
			
			Hero.resetHero();

			
			notChooseSex=false;
			notChooseNickname=false;
			
			Maps.loadFirstRoom();

			FXMLLoader loader = new FXMLLoader(LoadScene.class.getResource("LoadScene.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scenaCaricamento = new Scene(root, 1024,720); 
			Main.window.setScene(scenaCaricamento);
		}
		
    }

}

