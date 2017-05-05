package tunnelrunner;

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
 * Slightly more complex platforming game. Based off of Mike Slattery's World1 example
 * 
 * @author Nathan Arpin and Andrew Webber
 *
 */
public class Runner extends Application {

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

    private void constructWorld(Group root) {

        final PhongMaterial voidMaterial = new PhongMaterial();
        voidMaterial.setDiffuseColor(Color.rgb(0, 0, 0, 0.8));

        // Black void at the bottom of the map, to add a reference point to make
        // it easier to know when the player is falling and to make it fairly
        // clear that falling is not good
        Box voidBox = new Box(10000, 10, 10000);
        voidBox.setMaterial(voidMaterial);
        voidBox.setTranslateY(1100);

        // Material for all platforms
        final PhongMaterial platformMaterial = new PhongMaterial();
        platformMaterial.setDiffuseColor(Color.GREEN);
        platformMaterial.setSpecularColor(Color.LIGHTGREEN);

        // Beginning platform, starts right underneath the player
        Box homePlatform = new Box(100, 10, 100);
        homePlatform.setMaterial(platformMaterial);
        homePlatform.setTranslateX(0);
        homePlatform.setTranslateY(30);
        homePlatform.setTranslateZ(0);
        platforms.add(homePlatform);
        
        Box bottom = new Box(200, 10, 100);
        bottom.setMaterial(platformMaterial);
        bottom.setTranslateX(0);
        bottom.setTranslateY(30);
        bottom.setTranslateZ(200);
        platforms.add(bottom);
        
        Box bottomLeft = new Box(200, 10, 100);
        bottomLeft.setMaterial(platformMaterial);
        bottomLeft.setTranslateX(-175);
        bottomLeft.setTranslateY(-43);
        bottomLeft.setTranslateZ(200);
        bottomLeft.setRotate(45);
        platforms.add(bottomLeft);
        
        Box bottomRight = new Box(200, 10, 100);
        bottomRight.setMaterial(platformMaterial);
        bottomRight.setTranslateX(175);
        bottomRight.setTranslateY(-43);
        bottomRight.setTranslateZ(200);
        bottomRight.setRotate(-45);
        platforms.add(bottomRight);

        Box middleLeft = new Box(200, 10, 100);
        middleLeft.setMaterial(platformMaterial);
        middleLeft.setTranslateX(-247);
        middleLeft.setTranslateY(-220);
        middleLeft.setTranslateZ(200);
        middleLeft.setRotate(90);
        platforms.add(middleLeft);
        
        Box topLeft = new Box(200, 10, 100);
        topLeft.setMaterial(platformMaterial);
        topLeft.setTranslateX(-175);
        topLeft.setTranslateY(-397);
        topLeft.setTranslateZ(200);
        topLeft.setRotate(-45);
        platforms.add(topLeft);

        root.getChildren().addAll(platforms);
        root.getChildren().addAll(voidBox);
    }

    // Keeps track of the player's movement, particularly jumping
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
        // I made the field of view a bit bigger because it felt too cramped to
        // me
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

            if (keycode == KeyCode.A) delta = new Point3D(-5, delta.getY(), delta.getZ());
            if (keycode == KeyCode.D) delta = new Point3D(5, delta.getY(), delta.getZ());
            if (keycode == KeyCode.W) delta = new Point3D(delta.getX(), delta.getY(), 5);
            if (keycode == KeyCode.S) delta = new Point3D(delta.getX(), delta.getY(), -5);
            // Jump
            if (keycode == KeyCode.SPACE) delta = new Point3D(delta.getX(), -change / 2, delta.getZ());
        });

        scene.setOnKeyReleased(event -> {
            KeyCode keycode = event.getCode();

            if (keycode == KeyCode.A) delta = new Point3D(0, delta.getY(), delta.getZ());
            if (keycode == KeyCode.D) delta = new Point3D(0, delta.getY(), delta.getZ());
            if (keycode == KeyCode.W) delta = new Point3D(delta.getX(), delta.getY(), 0);
            if (keycode == KeyCode.S) delta = new Point3D(delta.getX(), delta.getY(), 0);
        });

        // Use mouse to control camera rotation
        scene.setOnMousePressed(me -> {
            mousePosX = me.getSceneX();
        });

        // I changed this to only rotate around the y axis, if the player looked
        // up it would mess up my movement scheme and nothing here is so high or
        // low that you need to look up or down
        scene.setOnMouseDragged(me -> {
            mouseOldX = mousePosX;
            mousePosX = me.getSceneX();
            mouseDeltaX = (mousePosX - mouseOldX);

            yRotate.setAngle(((yRotate.getAngle() - mouseDeltaX * 0.2) % 360 + 540) % 360 - 180); // +
        });

        KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS), e -> {
            // update position
            update();
        });
        Timeline mainLoop = new Timeline(kf);
        mainLoop.setCycleCount(Animation.INDEFINITE);
        mainLoop.play();

        primaryStage.setTitle("Platformer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void update() {
        // Move the camera
        Point3D delta2 = camera.localToParent(delta);
        cameraDolly.setTranslateX(cameraDolly.getTranslateX() + delta2.getX());
        cameraDolly.setTranslateY(cameraDolly.getTranslateY() + delta2.getY());
        cameraDolly.setTranslateZ(cameraDolly.getTranslateZ() + delta2.getZ());

        // Create a larger box to hold the camera so it doesn't rest directly on
        // the platforms
        Box player = new Box(10, 90, 10);
        player.setTranslateX(cameraDolly.getTranslateX());
        player.setTranslateY(cameraDolly.getTranslateY());
        player.setTranslateZ(cameraDolly.getTranslateZ());
        boolean resting = false;
        for (Shape3D shape : platforms) {
            if (player.getBoundsInParent().intersects(shape.getBoundsInParent())) {
                resting = true;
                delta = new Point3D(delta.getX(), 0, delta.getZ());
                cameraDolly.setTranslateY(shape.getTranslateY() - 50);
                cameraDolly.setRotate(shape.getRotate());
            }
        }
        // If the camera is in the air, make it fall down
        if (!resting) delta = delta.add(0, 0.5, 0);

        // If the player has fallen off the edge, reset position to the
        // beginning
        if (cameraDolly.getTranslateY() > 1000) {
            cameraDolly.setTranslateX(0);
            cameraDolly.setTranslateY(0);
            cameraDolly.setTranslateZ(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}