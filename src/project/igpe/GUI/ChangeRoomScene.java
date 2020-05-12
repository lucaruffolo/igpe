package project.igpe.GUI;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChangeRoomScene {

    @FXML
    private AnchorPane AnchorPaneBlack;

    @FXML
    private ImageView ImageId;
    
    private double opacity=0;
    
    
    public void SwitchRoom() {
    	AnchorPaneBlack.setOpacity(opacity);
    }
    
    
	public double getOpacity() {
		return opacity;
	}


	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
    
    
}

