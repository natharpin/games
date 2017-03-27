package dropper.window;

import java.util.ArrayList;
import java.util.Random;

import dropper.datastructures.Level;
import dropper.datastructures.Point;
import dropper.entities.Ball;
import dropper.entities.Bucket;
import dropper.entities.Coin;
import dropper.entities.DropperArea;
import dropper.entities.MovingPlatform;
import dropper.entities.Platform;
import dropper.entities.SpinningPlatform;
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

/**
 * A simple pachinko game, drop the ball into the buckets while trying to
 * collect coins
 * 
 * @author Nathan Arpin and Andrew Webber
 *
 */
public class Driver extends Application {

    GraphicsContext gc;
    DropperArea dropArea;

    int score = 0;

    void initialize() {
        dropArea = new DropperArea();
        initLevels();
    }

    void setHandlers(Scene scene) {
        scene.setOnMousePressed(e -> {
            dropArea.click();
        });
        scene.setOnMouseMoved(e -> {
            dropArea.mouseMove(e.getX(), e.getY());
        });
    }

    ArrayList<Coin> coins = new ArrayList<Coin>();

    Level levels[] = new Level[1];

    void initLevels() {
        coins.add(new Coin(150, 150, 10));
        coins.add(new Coin(150, 200, 10));
        coins.add(new Coin(150, 300, 10));
        coins.add(new Coin(150, 400, 10));
        coins.add(new Coin(200, 200, 10));
        coins.add(new Coin(300, 300, 10));
        coins.add(new Coin(400, 400, 10));
        levels[0] = new Level(new Platform[]
        {
                new Platform(225, 375, 50, 50, 120), new Platform(225, 375, 50, 50, -120),
                new Platform(0, 100, 10, WindowSettings.HEIGHT - 100, 1), new Platform(300, 500, 100, 25, 0),
                new Platform(WindowSettings.WIDTH - 10, 100, 10, WindowSettings.HEIGHT - 100, -1)
        }, new MovingPlatform[]
        {
                new MovingPlatform(10, 101, 25, 10, -30, new Point(WindowSettings.WIDTH - 25 - 10, 0), new Point(1, 0))
        }, new SpinningPlatform[]
        {
                new SpinningPlatform(WindowSettings.WIDTH / 2, 300, 50, 10)
        }, new Bucket[]
        {
                new Bucket(0, WindowSettings.HEIGHT - 30, WindowSettings.WIDTH / 3, 30, 50),
                new Bucket(WindowSettings.WIDTH / 3, WindowSettings.HEIGHT - 30, WindowSettings.WIDTH / 3, 30, 100),
                new Bucket((WindowSettings.WIDTH / 3) * 2, WindowSettings.HEIGHT - 30, WindowSettings.WIDTH / 3, 30, 50)
        }, coins);
    }

    /**
     * Update variables for one time step
     */
    public void update() {
        
        for (MovingPlatform p : levels[0].movers) {
            p.update();
        }

        for (SpinningPlatform p : levels[0].spinners) {
            p.update();
        }
        
        dropArea.update();
        score += dropArea.checkCollisions(levels[0]);
        
        for (Coin c : levels[0].coins) {
            c.update();
        }
        
    }

    /**
     * Draw the game world
     */

    void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT);

        for (Platform p : levels[0].platforms) {
            p.render(gc);
        }

        for (MovingPlatform p : levels[0].movers) {
            p.render(gc);
        }

        for (SpinningPlatform p : levels[0].spinners) {
            p.render(gc);
        }

        for (Bucket b : levels[0].buckets) {
            b.render(gc);
        }

        for (Coin c : levels[0].coins) {
            c.render(gc);
        }

        dropArea.render(gc);

        gc.setStroke(Color.BLACK);
        gc.strokeText("" + score, 10, 15);
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
