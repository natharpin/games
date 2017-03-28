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
import dropper.entities.Powerup;
import dropper.entities.SpinningPlatform;
import dropper.entities.SplitPowerup;
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
    
    int currentLevel = 0;
    int state = 1;
    int score = 0;

    void initialize() {
        dropArea = new DropperArea();
        initLevels();
    }

    void setHandlers(Scene scene) {
        scene.setOnMousePressed(e -> {
            if(state == 0)
                dropArea.click();
            if(state == 1)
                state = 0;
        });
        scene.setOnMouseMoved(e -> {
            dropArea.mouseMove(e.getX(), e.getY());
        });
    }

    ArrayList<Coin> coins = new ArrayList<Coin>();
    ArrayList<Powerup> powers = new ArrayList<Powerup>();

    Level levels[] = new Level[1];

    void initLevels() {
        coins.add(new Coin(150, 150, 10));
        coins.add(new Coin(150, 200, 10));
        coins.add(new Coin(150, 300, 10));
        coins.add(new Coin(150, 400, 10));
        coins.add(new Coin(200, 200, 10));
        coins.add(new Coin(300, 300, 10));
        coins.add(new Coin(400, 400, 10));
        
        powers.add(new SplitPowerup(300, 100));
        
        levels[0] = new Level(new Platform[]
        {
                new Platform(225, 375, 50, 50, 120), new Platform(225, 375, 50, 50, -120), new Platform(300, 500, 100, 25, 0),
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
        }, coins, powers);
    }

    /**
     * Update variables for one time step
     */
    public void update() {
        
        if(score > 1000){
            score = 0;
            currentLevel++;
            state = 1;
        }
        
        if(dropArea.getLives() <= 0 && dropArea.isDead()){
            state = 3;
        }
        
        if(state == 0){
            for (MovingPlatform p : levels[currentLevel].movers) {
                p.update();
            }
    
            for (SpinningPlatform p : levels[currentLevel].spinners) {
                p.update();
            }
            
            for (Coin c : levels[0].coins) {
                c.update();
            }
            
            dropArea.update();
            score += dropArea.checkCollisions(levels[currentLevel]);
        }
    }

    /**
     * Draw the game world
     */

    void render(GraphicsContext gc) {
        
        if(state == 1){
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.WHITE);
            gc.fillRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT);
            gc.strokeText("Click to start level", WindowSettings.WIDTH / 2 - 50, WindowSettings.HEIGHT / 2 - 10);
            return;
        }
        
        if(state == 3){
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.WHITE);
            gc.fillRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT);
            gc.strokeText("You lose!", WindowSettings.WIDTH / 2 - 50, WindowSettings.HEIGHT / 2 - 10);
            return;
        }
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT);

        for (Platform p : levels[currentLevel].platforms) {
            p.render(gc);
        }

        for (MovingPlatform p : levels[currentLevel].movers) {
            p.render(gc);
        }

        for (SpinningPlatform p : levels[currentLevel].spinners) {
            p.render(gc);
        }

        for (Bucket b : levels[currentLevel].buckets) {
            b.render(gc);
        }

        for (Coin c : levels[currentLevel].coins) {
            c.render(gc);
        }
        
        for(Powerup p : levels[currentLevel].powerups){
            p.render(gc);
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
