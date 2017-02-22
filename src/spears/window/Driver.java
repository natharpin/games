package spears.window;

import java.util.ArrayList;
import java.util.Random;

import spears.datastructures.*;
import spears.entities.*;
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

/*
 * Small driver class to run spear throwing game
 * Made by Nathan Arpin with boiler-plate code written by Dr. Mike Slattery
 * Programming Computer Games
 */
public class Driver extends Application {
    private Javelin turret;
    private ArrayList<Target> targets;
    private GraphicsContext gc;
    private int score, round;
    private Random rng;

    /**
     * Set up initial data structures/values
     */
    void initialize() {
        turret = new Javelin(new Point(WindowSettings.WIDTH / 2, WindowSettings.HEIGHT / 2),
                new Point(WindowSettings.WIDTH / 2, WindowSettings.HEIGHT / 2));
        targets = new ArrayList<Target>();
        score = 0;
        round = 0;
        rng = new Random();
        spawnTargets();
    }

    void setHandlers(Scene scene) {
        scene.setOnMousePressed(e -> {
            turret.shoot();
            render(gc);
        });

        scene.setOnMouseMoved(e -> {
            turret.setEnd(new Point((int) e.getX(), (int) e.getY()));
            render(gc);
        });
    }

    /**
     * Update variables for one time step
     */
    public void update() {
        turret.update();
        for (int i = 0; i < targets.size(); i++) {
            Target target = targets.get(i);
            if (turret.checkCollision(target)) {
                score++;
                targets.remove(i);
                if (targets.size() == 0) spawnTargets();
            }
            else
                target.move();
        }
    }

    
    /*
     * Spawn new targets once all previous targets have been hit
     */
    private void spawnTargets() {
        int numTargets = round % 5 + 1;
        int targetSpeed = round / 5 + 1;
        int targetSize = 30 - lesser((round / 5), 20);

        for (int i = 0; i < numTargets; i++) {
            targets.add(new Target(
                    new Point(rng.nextInt(WindowSettings.WIDTH - 30) + 15,
                            rng.nextInt(WindowSettings.HEIGHT - 30) + 15),
                    new Vector(new Point(), randomSpeedPoint(targetSpeed)), targetColor(round), targetSize));
        }
        round++;
    }

    // Returns the lower of the two given integers
    private int lesser(int a, int b) {
        return (a < b) ? a : b;
    }

    // Cycles through colors every fifth round
    private Color targetColor(int round) {
        switch (round % 21 / 5) {
            case 0:
                return Color.GREEN;
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.ORANGE;
            default:
                return Color.BLACK;
        }
    }

    // Give a target a random initial direction
    private Point randomSpeedPoint(int targetSpeed) {
        if (rng.nextBoolean()) {
            if (rng.nextBoolean())
                return new Point(targetSpeed, targetSpeed);
            else
                return new Point(targetSpeed, -targetSpeed);
        }
        else {
            if (rng.nextBoolean())
                return new Point(-targetSpeed, targetSpeed);
            else
                return new Point(-targetSpeed, -targetSpeed);
        }
    }

    /**
     * Draw the game world
     */
    void render(GraphicsContext gc) {
        // fill background
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT);

        gc.setFill(Color.BLACK);
        gc.fillText("" + score, WindowSettings.WIDTH / 2, WindowSettings.HEIGHT / 2 - 30);

        turret.render(gc);
        for (Target target : targets)
            target.render(gc);
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
