package project.igpe.GUI;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    
    public static void SleepScene (Scene Scena) throws Exception {

    	 Circle circle = new Circle();
         circle.setCenterX(300.0f); 
         circle.setCenterY(135.0f);      
         circle.setRadius(5000.0f); 
         circle.setFill(Color.BLACK); 
        
         FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000)); 
         fadeTransition.setNode(circle); 
         fadeTransition.setFromValue(0); 
         fadeTransition.setToValue(1);   
         fadeTransition.setCycleCount(50); 
         fadeTransition.setAutoReverse(false); 
         fadeTransition.setCycleCount(1);
         fadeTransition.play();

         Group root = new Group(circle); 
         Scene zorro = new Scene(root,1270, 900);
         Main.window.setScene(zorro);
         fadeTransition.setOnFinished(null); 
         System.out.println(fadeTransition.getStatus());
         if (fadeTransition.getDuration().equals(999)) {
        	 System.out.println("arrivato");
             fadeTransition.stop(); 

         }
       //  Main.window.setScene(Scena);
         

        System.out.println("ok");

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {}

    
}
