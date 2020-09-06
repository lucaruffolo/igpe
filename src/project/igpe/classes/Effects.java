package project.igpe.classes;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Effects {
	
	public static AudioClip audioclip;
	private static double valoreEffetti;
	public static AudioClip shootgun = new AudioClip(new File("src/project/igpe/sounds/pistolas.wav").toURI().toString());
	
	public static void setEffects(String effectsFile) {
		audioclip = new AudioClip(new File(effectsFile).toURI().toString());
	}
	
	public static void modifyVolumeEffetcs(double valoreEffects) {
		audioclip.setVolume(valoreEffects);
	}
	
	public static void EffectsStart() {
		audioclip.play();
	}
	
	public static void EffectsStop() {
		audioclip.stop();
	}

	public static void EffectsLoop() {
		audioclip.setCycleCount(AudioClip.INDEFINITE);
	}
	
	
	

	public static double getValoreEffetti() {
		return valoreEffetti;
	}

	public static void setValoreEffetti(double valoreEffetti) {
		Effects.valoreEffetti = valoreEffetti;
	}
	
	
}
