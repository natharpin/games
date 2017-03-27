package dropper.entities;

import java.util.ArrayList;

import dropper.datastructures.Level;
import dropper.datastructures.Point;
import dropper.window.WindowSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Controls the area above the level that drops the ball and all the ball
 * collisions
 * 
 * @author Nathan and Andrew
 *
 */
public class DropperArea extends Sprite {

    private Ball inBox;
    private ArrayList<Ball> inFreefall;
    private int bounceCooldown, lives;

    public DropperArea() {
        this.x = 0;
        this.y = 0;
        this.width = 10;
        this.bounceCooldown = 0;
        inFreefall = new ArrayList<Ball>();
        lives = 10;
    }

    public int getLives(){
        return lives;
    }
    
    public void click() {
        if (inFreefall.isEmpty()) {
            inFreefall.add(new Ball(followBallX(), followBallY()));
            lives--;
        }
    }

    public void mouseMove(double x, double y) {
        this.x = x - this.width / 2;
        this.y = y - this.width / 2;
    }

    private double min(double a, double b) {
        if (a < b) return a;
        return b;
    }

    private double followBallY() {
        if (this.y < 0) return 0;
        return min(this.y, WindowSettings.HEIGHT / 8 - this.width);
    }

    private double followBallX() {
        if (this.x < 0) return 0;
        if (this.x > WindowSettings.WIDTH - this.width) return WindowSettings.WIDTH - this.width;
        return this.x;
    }

    public int checkCollisions(Level level) {
        int score = 0;
        if (inFreefall.isEmpty()) return score;
        for (int i = 0; i < inFreefall.size(); i++) {
            for (Platform p : level.platforms) {
                if (p.intersects(inFreefall.get(i).next()) && bounceCooldown < 0) {
                    bounceCooldown = 2;
                    p.bounce(inFreefall.get(i));
                }
            }
            for (Platform p : level.movers) {
                if (p.intersects(inFreefall.get(i).next()) && bounceCooldown < 0) {
                    bounceCooldown = 2;
                    p.bounce(inFreefall.get(i));
                }
            }
            for (Platform p : level.spinners) {
                if (p.intersects(inFreefall.get(i).next()) && bounceCooldown < 0) {
                    bounceCooldown = 2;
                    p.bounce(inFreefall.get(i));
                }
            }
            for (Bucket b : level.buckets) {
                if (inFreefall.isEmpty()) return score;
                if (b.intersects(inFreefall.get(i).next())) {
                    inFreefall.remove(i);
                    score += b.score;
                    continue;
                }
            }
            for (int j = 0; j < level.coins.size(); j++) {
                if (inFreefall.isEmpty()) return score;
                if (level.coins.get(j).intersects(inFreefall.get(i).next())) {
                    score += 25;
                    level.coins.remove(j);
                }
            }
            
            for(int k = 0; k < level.powerups.size(); k++){
                if(level.powerups.get(k).intersects(inFreefall.get(i))){
                    if(level.powerups.get(k) instanceof SplitPowerup){
                        split(i);
                        level.powerups.remove(k);
                    }
                    if(level.powerups.get(k) instanceof AddLifePowerup){
                        lives++;
                        level.powerups.remove(k);
                    }
                }
            }
        }
        return score;
    }

    private void split(int ball){
        double x = inFreefall.get(ball).x;
        double y = inFreefall.get(ball).y;
        
        inFreefall.remove(ball);
        
        inFreefall.add(new Ball(x, y, -3, -3));
        inFreefall.add(new Ball(x, y, 0, -3));
        inFreefall.add(new Ball(x, y, 3, -3));
    }
    
    public void update() {
        bounceCooldown--;
        for (int i = 0; i < inFreefall.size(); i++) {
            inFreefall.get(i).update();
            if (inFreefall.get(i).y > WindowSettings.HEIGHT) inFreefall.remove(i);
            if (inFreefall.get(i).x < 0 || inFreefall.get(i).x > WindowSettings.WIDTH) inFreefall.remove(i);
        }
        inBox = new Ball(followBallX(), followBallY());
    }

    public void render(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT / 8);
        if (inBox != null) inBox.render(gc);
        for (int i = 0; i < inFreefall.size(); i++) {
            inFreefall.get(i).render(gc);
        }
        gc.strokeText("" + lives, WindowSettings.WIDTH - 20, 20);
    }
}
