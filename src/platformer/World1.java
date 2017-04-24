package platformer;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Simple 3D World
 * 
 * Based on code from JavaFX 8 Introduction by Example
 *   (code written by Sean Phillips)
 * @author mike slattery
 */
public class World1 extends Application {

	private PerspectiveCamera camera;
	private Group cameraDolly;
	private final double cameraQuantity = 20.0;
	private final double sceneWidth = 600;
	private final double sceneHeight = 600;
	private final double FPS = 60;

	private double mousePosX;
	private double mouseOldX;
	private double mouseDeltaX;

	private ArrayList<Shape3D> platforms = new ArrayList<Shape3D>();
	
	private Sphere goal;
	private BorderPane borderPane;
	private boolean complete = false;
	
	private void constructWorld(Group root) {

		final PhongMaterial platformMaterial = new PhongMaterial();
		platformMaterial.setDiffuseColor(Color.GREEN);
		platformMaterial.setSpecularColor(Color.LIGHTGREEN);
		
		Box homePlatform = new Box(100, 10, 100);
		homePlatform.setMaterial(platformMaterial);
		homePlatform.setTranslateX(0);
		homePlatform.setTranslateY(30);
		homePlatform.setTranslateZ(0);
		platforms.add(homePlatform);
		
		Box step1 = new Box(50, 10, 50);
		step1.setMaterial(platformMaterial);
		step1.setTranslateX(0);
		step1.setTranslateY(0);
		step1.setTranslateZ(150);
		platforms.add(step1);
		
		Box step2 = new Box(100, 10, 100);
		step2.setMaterial(platformMaterial);
		step2.setTranslateX(0);
		step2.setTranslateY(-20);
		step2.setTranslateZ(300);
		platforms.add(step2);
		
		Box step3 = new Box(100, 10, 100);
		step3.setMaterial(platformMaterial);
		step3.setTranslateX(0);
		step3.setTranslateY(-20);
		step3.setTranslateZ(500);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(1000), step3);
		tt.setAutoReverse(true);
		tt.setCycleCount(Animation.INDEFINITE);
		tt.setFromZ(500);
		tt.setToZ(600);
		tt.play();
		
		platforms.add(step3);
		
		Cylinder step4sub1 = new Cylinder(15, 200);
		step4sub1.setMaterial(platformMaterial);
		step4sub1.setTranslateX(0);
		step4sub1.setRotate(90);
		step4sub1.setTranslateY(-20);
		step4sub1.setTranslateZ(800);
		platforms.add(step4sub1);
		
		RotateTransition rt = new RotateTransition(Duration.millis(3000), step4sub1);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setAxis(Rotate.Z_AXIS);
		rt.setByAngle(360);
		rt.play();

		Cylinder step4sub2 = new Cylinder(15, 200);
		step4sub2.setMaterial(platformMaterial);
		step4sub2.setTranslateX(0);
		step4sub2.setRotationAxis(Rotate.X_AXIS);
		step4sub2.setRotate(90);
		step4sub2.setTranslateY(-20);
		step4sub2.setTranslateZ(800);
		platforms.add(step4sub2);
		
		Box step5sub1 = new Box(200, 10, 20);
		step5sub1.setMaterial(platformMaterial);
		step5sub1.setTranslateX(0);
		step5sub1.setTranslateY(30);
		step5sub1.setTranslateZ(1000);
		platforms.add(step5sub1);
		
		TranslateTransition tt1 = new TranslateTransition(Duration.millis(1000), step5sub1);
		tt1.setAutoReverse(true);
		tt1.setCycleCount(Animation.INDEFINITE);
		tt1.setByX(100);
		tt1.play();
		
		Box step5sub2 = new Box(200, 10, 20);
		step5sub2.setMaterial(platformMaterial);
		step5sub2.setTranslateX(0);
		step5sub2.setTranslateY(100);
		step5sub2.setTranslateZ(1150);
		platforms.add(step5sub2);
		
		TranslateTransition tt2 = new TranslateTransition(Duration.millis(1500), step5sub2);
		tt2.setAutoReverse(true);
		tt2.setCycleCount(Animation.INDEFINITE);
		tt2.setByX(-150);
		tt2.play();
		
		Box step5sub3 = new Box(200, 10, 20);
		step5sub3.setMaterial(platformMaterial);
		step5sub3.setTranslateX(-200);
		step5sub3.setTranslateY(30);
		step5sub3.setTranslateZ(1300);
		platforms.add(step5sub3);
		
		TranslateTransition tt3 = new TranslateTransition(Duration.millis(4000), step5sub3);
		tt3.setAutoReverse(true);
		tt3.setCycleCount(Animation.INDEFINITE);
		tt3.setByX(300);
		tt3.play();
		
		Box finalPlatform = new Box(200, 10, 200);
		finalPlatform.setMaterial(platformMaterial);
		finalPlatform.setTranslateX(0);
		finalPlatform.setTranslateY(-30);
		finalPlatform.setTranslateZ(1600);
		platforms.add(finalPlatform);
		
		final PhongMaterial goalMaterial = new PhongMaterial();
		goalMaterial.setDiffuseColor(Color.rgb(100, 0, 0, 0.5));
		goalMaterial.setSpecularColor(Color.PINK);
		
		goal = new Sphere(10);
		goal.setMaterial(goalMaterial);
		goal.setTranslateX(0);
		goal.setTranslateY(-60);
		goal.setTranslateZ(1600);
		
        Text text = new Text();
        text.setText("You won!");
        text.setStyle("-fx-font-size: 20;");
        text.setCache(true);

        borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-color: black;-fx-background-color: #66CCFF;");
        borderPane.setTop(text);
        borderPane.setTranslateX(-45);
        borderPane.setTranslateY(-90);
        borderPane.setTranslateZ(1700);
        borderPane.setOpacity(0);
		
		root.getChildren().addAll(platforms);
		root.getChildren().add(goal);
		root.getChildren().add(borderPane);
	}

	Point3D delta = new Point3D(0, 0, 0);
	
	@Override
	public void start(Stage primaryStage) {

		// Build your Scene and Camera
		Group sceneRoot = new Group();
		constructWorld(sceneRoot);

		Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight, true);
		scene.setFill(Color.CYAN);
		camera = new PerspectiveCamera(true);
		camera.setNearClip(0.1);
		camera.setFarClip(10000.0);
		camera.setFieldOfView(80);
		scene.setCamera(camera);
		// translations through dolly
		cameraDolly = new Group();
		cameraDolly.setTranslateZ(0);
		cameraDolly.setTranslateX(0);
		cameraDolly.getChildren().add(camera);
		sceneRoot.getChildren().add(cameraDolly);
		// rotation transforms
		Rotate xRotate = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
		Rotate yRotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
		camera.getTransforms().addAll(xRotate, yRotate);
		
		// Use keyboard to control camera position
		scene.setOnKeyPressed(event -> {
			double change = cameraQuantity;
			// What key did the user press?
			KeyCode keycode = event.getCode();

			if(complete) return;
			
			if (keycode == KeyCode.A) {
				delta = new Point3D(-5, delta.getY(), delta.getZ());
			}
			if (keycode == KeyCode.D) {
				delta = new Point3D(5, delta.getY(), delta.getZ());
			}
			if (keycode == KeyCode.W) {
				delta = new Point3D(delta.getX(), delta.getY(), 5);
			}
			if (keycode == KeyCode.S) {
				delta = new Point3D(delta.getX(), delta.getY(), -5);
			}
			if(keycode == KeyCode.SPACE){
				delta = new Point3D(delta.getX(), -change / 2,delta.getZ());
			}
		});
		
		scene.setOnKeyReleased(event -> {
			KeyCode keycode = event.getCode();

			if(complete) return;
			
			if (keycode == KeyCode.A) {
				delta = new Point3D(0, delta.getY(), delta.getZ());
			}
			if (keycode == KeyCode.D) {
				delta = new Point3D(0, delta.getY(), delta.getZ());
			}
			if (keycode == KeyCode.W) {
				delta = new Point3D(delta.getX(), delta.getY(), 0);
			}
			if (keycode == KeyCode.S) {
				delta = new Point3D(delta.getX(), delta.getY(), 0);
			}
		});

		// Use mouse to control camera rotation
		scene.setOnMousePressed(me -> {
			if(complete) return;
			mousePosX = me.getSceneX();
		});

		scene.setOnMouseDragged(me -> {
			if(complete) return;
			mouseOldX = mousePosX;
			mousePosX = me.getSceneX();
			mouseDeltaX = (mousePosX - mouseOldX);

			yRotate.setAngle(((yRotate.getAngle() - mouseDeltaX * 0.2) % 360 + 540) % 360 - 180); // +
		});
		
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					// update position
					update();
				}
			);
		Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();

		primaryStage.setTitle("Platformer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	double changeX = 0, changeY = 0, changeZ = 0;
	
	public void update(){
		Point3D delta2 = camera.localToParent(delta);
		cameraDolly.setTranslateX(cameraDolly.getTranslateX() + delta2.getX());
		cameraDolly.setTranslateY(cameraDolly.getTranslateY() + delta2.getY());
		cameraDolly.setTranslateZ(cameraDolly.getTranslateZ() + delta2.getZ());

		Box player = new Box(10, 90, 10);
		player.setTranslateX(cameraDolly.getTranslateX());
		player.setTranslateY(cameraDolly.getTranslateY());
		player.setTranslateZ(cameraDolly.getTranslateZ());
		boolean resting = false;
		for(Shape3D shape : platforms){
			if(player.getBoundsInParent().intersects(shape.getBoundsInParent())){
				resting = true;
				delta = new Point3D(delta.getX(), 0, delta.getZ());
				cameraDolly.setTranslateY(shape.getTranslateY() - 50);
			}		
		}
		if(!resting)
			delta = delta.add(0, 0.5, 0);
		
		if(player.getBoundsInParent().intersects(goal.getBoundsInParent())){
			borderPane.setOpacity(1);
			complete = true;
			delta = new Point3D(0, 0, 0);
		}
		
		if(cameraDolly.getTranslateY() > 1000){
			cameraDolly.setTranslateX(0);
			cameraDolly.setTranslateY(0);
			cameraDolly.setTranslateZ(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}