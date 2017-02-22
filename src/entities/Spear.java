package entities;

import datastructures.Vector;
import javafx.scene.canvas.GraphicsContext;

/*
 * Simple projectile class
 */
public class Spear {

    private Vector shaft;
    private Vector speed;

    public Spear(Vector shaft, Vector speed) {
        this.shaft = shaft;
        this.speed = speed;
    }

    // Moves the spear forward by its speed
    public void move() {
        shaft.add(speed);
        shaft.addOrigin(speed);
    }

    public void render(GraphicsContext gc) {
        gc.strokeLine(shaft.getOrigin().getX(), shaft.getOrigin().getY(), shaft.getEnd().getX(), shaft.getEnd().getY());
    }

    // Returns the x coordinate of the head of the spear, used for collision
    // detection
    public int getHeadX() {
        return shaft.getEnd().getX();
    }

    // Returns the y coordinate of the head of the spear, used for collision
    // detection
    public int getHeadY() {
        return shaft.getEnd().getY();
    }
}
