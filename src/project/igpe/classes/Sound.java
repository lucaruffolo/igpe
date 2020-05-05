package project.igpe.classes;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	
	private static Media gamesound;
	private static MediaPlayer mediaPlayer;
	
	public static void setMusic(String musicFile) {
		gamesound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(gamesound);
	}
	
	public static void modifyVolume(double x) {
		mediaPlayer.setVolume(x);
	}
	
	public static void musicStart() {
			mediaPlayer.play();
	}
	
	public static void musicStop() {
		mediaPlayer.stop();
	}

	
	
	
}