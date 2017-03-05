package dropper.window;

import java.util.ArrayList;
import java.util.Random;

import dropper.entities.Ball;
import dropper.entities.DropperArea;
import dropper.entities.Platform;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {

	GraphicsContext gc;
	DropperArea dropArea;

	void initialize() {
		dropArea = new DropperArea();
	}

	void setHandlers(Scene scene) {
		scene.setOnMousePressed(e -> {
			dropArea.click();
		});
		scene.setOnMouseMoved(e -> {
			dropArea.mouseMove(e.getX(), e.getY());
		});
	}

	Platform levelOne[] = { new Platform(225, 375, 50, 50, 30), new Platform(0, 100, 10, 500, 0), new Platform(300, 200, 100, 25, 0) };

	/**
	 * Update variables for one time step
	 */
	public void update() {
		dropArea.update();
		dropArea.checkCollisions(levelOne);
	}

	/**
	 * Draw the game world
	 */
	void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT);
		
		for(Platform p : levelOne){
			p.render(gc);
		}

		dropArea.render(gc);
	}

	/*
	 * Begin boiler-plate code... [Animation and events with initialization]
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) {
		theStage.setTitle(WindowSettings.appName);

		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(WindowSettings.WIDTH, WindowSettings.HEIGHT);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();

		// Initial setup
		initialize();
		setHandlers(theScene);

		// Setup and start animation loop (Timeline)
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / WindowSettings.FPS), e -> {
			// update position
			update();
			// draw frame
			render(gc);
		});
		Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();

		theStage.show();
	}
	/*
	 * ... End boiler-plate code
	 */
}
