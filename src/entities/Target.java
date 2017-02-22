package entities;

import datastructures.Point;
import datastructures.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import window.WindowSettings;

/*
 * Simple target class
 */
public class Target {

    private int size;
    private Color color;

    private Point center;
    private Vector speed;

    // private int bounceCooldown = 0;

    // Simple constructor that sets position and speed variably and defaults
    // color and size
    public Target(Point center, Vector speed) {
        this.center = center;
        this.speed = speed;
        this.color = Color.BLACK;
        this.size = 30;
    }

    // Allows the color and size to be changed
    public Target(Point center, Vector speed, Color color, int size) {
        this.center = center;
        this.speed = speed;
        this.color = color;
        this.size = size;
    }

    public int getX() {
        return center.getX();
    }

    public int getY() {
        return center.getY();
    }

    public int getSize() {
        return size;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(center.getX() - size / 2, center.getY() - size / 2, size, size);
    }

    // Moves the target by its speed and bounces the target off the outside
    // walls
    public void move() {
        // bounceCooldown--;
        Point next = new Point(center.getX() + speed.getDx(), center.getY() + speed.getDy());
        if (next.getX() + size / 2 > WindowSettings.WIDTH || next.getX() - size / 2 < 0) {
            speed.mirrorX();
        }
        if (next.getY() + size / 2 > WindowSettings.HEIGHT || next.getY() - size / 2 < 0) {
            speed.mirrorY();
        }

        // This code was meant to allow the target to bounce off of the spear
        // thrower in the middle of the playing field, but the bouncing got too
        // complicated so I kept it simple and allowed targets to pass over the
        // thrower unaffected
        /*
         * if(Math.abs(next.getX() - WindowSettings.WIDTH / 2) < 30){
         * if(Math.abs(next.getY() - WindowSettings.HEIGHT / 2) < 30){
         * speed.mirror(); } }
         */

        center = new Point(center.getX() + speed.getDx(), center.getY() + speed.getDy());
    }
}
