package project.igpe.GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
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

	 
    @FXML	private TableView<ScoreBoardData> Table;
    
    @FXML   private TableColumn<ScoreBoardData, String> clmNameHero;
    @FXML   private TableColumn<ScoreBoardData, Boolean> clmSexHero;
	@FXML   private TableColumn<ScoreBoardData, Integer> clmKillEnemy;
	@FXML   private TableColumn<ScoreBoardData, Integer> clmAmmoShoot;
    @FXML   private TableColumn<ScoreBoardData, Integer> clmTimePlayed;   

    @FXML
    private Button bttBack;
    
    @FXML
    private Button bttReset;	  

    @FXML
    void clickBack(ActionEvent event) throws Exception {
    	dataList.clear();
    	FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("MenuIniziale.fxml"));
    	AnchorPane root = (AnchorPane) loader.load(); //carica l'AnchorPane principale
    	Scene menuIniziale = new Scene(root, 1024,720); 
    	Main.window.setScene(menuIniziale);
    }
   

    @FXML
    void clickReset(ActionEvent event) throws Exception {
    	PrintWriter writer = new PrintWriter("src/project/igpe/GUI/scoreboard.txt");
    	writer.print("");
    	writer.close();
    	dataList.clear();
    }          
    
    public static ObservableList<ScoreBoardData> dataList = FXCollections.observableArrayList();
    	
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {		

		clmNameHero.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, String>("nameHero"));
    	clmSexHero.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Boolean>("sex"));
    	clmKillEnemy.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Integer>("kill"));
    	clmAmmoShoot.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Integer>("shoot"));
    	clmTimePlayed.setCellValueFactory(new PropertyValueFactory<ScoreBoardData, Integer>("timePlayed"));   	
    	
    	Table.setItems(dataList);
  	
       	
		try {
			load();
		} catch (Exception e) {	e.printStackTrace();}
		
	}

	public static void load() throws Exception {
		
		
	    try {
			BufferedReader b = new BufferedReader(new FileReader("src/project/igpe/GUI/scoreboard.txt"));
			ArrayList<ScoreBoardData> arrayPlayer = new ArrayList<ScoreBoardData>();
			
			while (b.ready()) {					
				String line = b.readLine();
				StringTokenizer tok = new StringTokenizer(line, ";");
				while (tok.hasMoreTokens()) {	
					
			    	 ScoreBoardData t1 = new ScoreBoardData();			    	 
			    	 t1.setNameHero(tok.nextToken());
			    	 if (tok.nextToken().contentEquals("false"))
			    		 t1.setSex("Maschio");
			    	 else
			    		 t1.setSex("Femmina");
			    	 
			    	 t1.setKill(tok.nextToken());
			    	 t1.setShoot(tok.nextToken());
			    	 t1.setTimePlayed(tok.nextToken());
			    	 
			    	 arrayPlayer.add(t1);
			     }
			     
			}
			b.close();
			
			for (ScoreBoardData x : arrayPlayer) {
				dataList.add(x);
			}
			
			
		//	System.out.println("Scoreboard Caricata");	
		} catch (FileNotFoundException e) {
			//System.out.println("Scoreboard Non trovato");
			e.printStackTrace();
		}
	 }
	
    
}