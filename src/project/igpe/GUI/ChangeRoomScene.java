package project.igpe.GUI;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChangeRoomScene {

    @FXML
    private AnchorPane AnchorPaneBlack;

    @FXML
    private static ImageView ImageId;
    
    public void SwitchRoom() {
  //  	AnchorPaneBlack.setOpacity(opacity);
    }
    

	public static void setOpacity(double opacity) {

		ImageId.setOpacity(opacity);
	}
    
    
}

