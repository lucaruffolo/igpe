package project.igpe.GUI;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SleepThread extends Application {
    public static final double W = 200; // canvas dimensions.
    public static final double H = 200;

    public static final double D = 20;  // diameter.

    public void start(Scene scene) {
        DoubleProperty opacity  = new SimpleDoubleProperty();

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                    new KeyValue(opacity, 1)
            ),
            new KeyFrame(Duration.seconds(3),
                    new KeyValue(opacity, 0)
            )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);

        final Canvas canvas = new Canvas(W, H);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, 0, W, H);
                gc.setFill(Color.FORESTGREEN.deriveColor(0, 1, 1, opacity.get()));
                gc.fillOval(
                    W/2,
                    H/2,
                    D,
                    D
                );
            }
        };

        timer.start();
        timeline.play();
    }

    public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}  