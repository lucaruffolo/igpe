package project.igpe.GUI;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneHandler {
	
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
}
