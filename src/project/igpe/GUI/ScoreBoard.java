package project.igpe.GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import project.igpe.main.Main;

public class ScoreBoard implements Initializable{

    @FXML
    private TableView<ScoreBoardData> Table;
    
    @FXML
    private TableColumn<ScoreBoardData, String> clmNameHero;
    @FXML
    private TableColumn<ScoreBoardData, Boolean> clmSexHero;
	@FXML
    private TableColumn<ScoreBoardData, Integer> clmKillEnemy;
	@FXML
    private TableColumn<ScoreBoardData, Integer> clmAmmoShoot;
    @FXML
    private TableColumn<ScoreBoardData, Integer> clmTimePlayed;   

    @FXML
    private Button bttBack;
    
    @FXML
    private Button bttReset;	  

    @FXML
    void clickBack(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));
    	AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
    	Scene menuIniziale = new Scene(root, 1024,720); 
    	Main.window.setScene(menuIniziale);
    }
   
    private final ObservableList<ScoreBoardData> dataList = FXCollections.observableArrayList();

    @FXML
    void clickReset(ActionEvent event) throws Exception {

    
    	
    }          

	public static void load() throws Exception {
		
	    try {
			BufferedReader b = new BufferedReader(new FileReader("src/project/igpe/GUI/scoreboard.txt"));
				
				while (b.ready()) {
				String line = b.readLine();
				String user;
				ArrayList<String> arrayString = new ArrayList<String>();
				StringTokenizer tok = new StringTokenizer(line, ";");
			     while (tok.hasMoreTokens()) {			   
			    	 
			    	 user=tok.nextToken();
			    	 System.out.println(user);
			    	 arrayString.add(tok.nextToken()); //ISBN
			    	 arrayString.add(tok.nextToken()); //TITLE
			    	 arrayString.add(tok.nextToken()); //AUTHOR
			    	 arrayString.add(tok.nextToken()); //PUBLISHER
			    	 arrayString.add(tok.nextToken()); //YEAR
			    }
			     
			}
			b.close();
			System.out.println("Scoreboard Caricata");	
		} catch (FileNotFoundException e) {
			System.out.println("Scoreboard Non trovato");
			e.printStackTrace();
		}
	      
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		clmNameHero.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, String>("Nome Hero"));
    	clmSexHero.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Boolean>("Sesso"));
    	clmKillEnemy.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Integer>("Nemici Uccisi"));
    	clmAmmoShoot.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Integer>("Colpi Sparati"));
    	clmTimePlayed.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Integer>("Tempo Di Gioco"));
    	ScoreBoardData t1 = new ScoreBoardData("zorro", false, 0, 1, 2);
    	ScoreBoardData t2 = new ScoreBoardData("zor2ro", true, 0, 1, 2);
    	dataList.addAll(t1,t2);
    	
    	Table.setItems(dataList);
    	/*
		try {
			load();
		} catch (Exception e) {	e.printStackTrace();}
		*/
	}
}