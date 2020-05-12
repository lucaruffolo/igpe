package project.igpe.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import project.igpe.main.Main;

public class SceneHandler implements Initializable{
	
    private static HashMap<String, Pane> allWindows = new HashMap<String,Pane>();
    
    private static Scene main;

    public static void init(Scene m) {
        main = m;        	
    }

    public static void add(String name, Pane pane){
         allWindows.put(name, pane);
    }
    
    public static void setCurrent(String name){
    	main.setRoot(allWindows.get(name));
    }	
    
    public static void SleepScene (Scene prima,Scene prossima) throws Exception {
   
    	FXMLLoader loaderSleep = new FXMLLoader(ChangeRoomScene.class.getResource("ChangeRoomScene.fxml"));
    	AnchorPane rootSleep = null;
		try {rootSleep = (AnchorPane) loaderSleep.load();} catch (IOException e2) {e2.printStackTrace();	}
		Scene menuSleep = new Scene(rootSleep, 1270, 900);		
		Main.window.setScene(menuSleep);

		System.out.println("inizio");


		System.out.println("fine");
		Main.window.setScene(prossima);
		

		/*
		ChangeRoomScene.setOpacity(0);
		Main.window.setScene(menuSleep);
		Main.window.centerOnScreen();
			
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
			ChangeRoomScene.setOpacity(0.20);
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
			ChangeRoomScene.setOpacity(0.40);
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
			ChangeRoomScene.setOpacity(0.80);
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
				ChangeRoomScene.setOpacity(1);
		
		Main.window.setOpacity(1);
		Main.window.setScene(ripristinaScena);
		Main.window.centerOnScreen();
				
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
			ChangeRoomScene.setOpacity(0.80);
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
			ChangeRoomScene.setOpacity(0.50);
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
			ChangeRoomScene.setOpacity(0.30);
			try {	TimeUnit.MICROSECONDS.sleep(10);	} catch (InterruptedException e2) {	e2.printStackTrace();}
				ChangeRoomScene.setOpacity(0);
			
		*/
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {}

    
}
