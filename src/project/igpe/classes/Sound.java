package project.igpe.classes;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	
	private static Media gamesound;
	private static MediaPlayer mediaPlayer;
	private static double valoreMusica;
	
	
	public static void setMusic(String musicFile) {
		gamesound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(gamesound);
	}
	
	public static void modifyVolume(double valoreMusica) {
		mediaPlayer.setVolume(valoreMusica);
	}
	
	public static void musicStart() {
		mediaPlayer.play();
	}
	
	public static void musicStop() {
		mediaPlayer.stop();
	}

	public static void musicLoop() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}
	
	
	public static double getValoreMusica() {
		return valoreMusica;
	}

	public static void setValoreMusica(double valoreMusica) {
		Sound.valoreMusica = valoreMusica;
	}

	
}