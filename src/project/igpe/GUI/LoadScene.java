package project.igpe.GUI;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import project.igpe.classes.Hero;
import project.igpe.classes.Settings;
import project.igpe.main.Main;

public class LoadScene{
	
	private boolean ready = false;
	
	@FXML
    private Text textPressToContinue;
	
    @FXML
    private ProgressBar ProgressBar;
    
    @FXML
    void ClickEnter(KeyEvent event) {
    	if(ready) {
			Settings.valueOfProgressBar = 0;
    		Main.startGame();
    		Hero.TimeStartPlayed = System.currentTimeMillis();    		
    	}
    }

    public void initialize() {
    	
    	Thread t = new Thread(task);
    	ProgressBar.setProgress(Settings.valueOfProgressBar);
    	ProgressBar.setOnKeyPressed(new EventHandler<KeyEvent>()  {
    		   @Override
    		   public void handle(KeyEvent ke)
    		   {
    			   ClickEnter(ke);
    		   }
    		 });
    	ProgressBar.setFocusTraversable(true);
    	t.setDaemon(true);
    	t.start();
    	
    	task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		
			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				textPressToContinue.setOpacity(1);
				ready=true;
			}
			
		});
    	
    }
    
    Task<Void> task = new Task<Void>() {
    	
		@Override
		protected Void call() throws Exception {
			
			while(Settings.valueOfProgressBar <= 1) {
				Settings.valueOfProgressBar += 0.005;
				ProgressBar.setProgress(Settings.valueOfProgressBar);
				//System.out.println(Settings.valueOfProgressBar);
				Thread.sleep(10);
			}
			return null;
		}
		
    };    
    
    
}
