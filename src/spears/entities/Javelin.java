package spears.entities;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spears.datastructures.*;
import spears.window.WindowSettings;

/*
 * Javelin class tracks the mouse movements and generates projectile objects called spears. 
 * Purges spears once they have gone off screen to reduce processing time for useless objects.
 */
public class Javelin {

    private int size = 30;

    private ArrayList<Spear> spears;
    private Point center, end;

    public Javelin(Point center, Point end) {
        this.center = center;
        this.end = end;
        spears = new ArrayList<Spear>();
    }

    // Moves each spear and checks and deletes spears that have moved offscreen
    public void update() {
        purge();
        for (Spear spear : spears)
            spear.move();
    }

    // Removes offscreen spears
    public void purge() {
        for (int i = 0; i < spears.size(); i++) {
            Spear spear = spears.get(i);
            if (!isWithinBounds(spear)) spears.remove(i);
        }
    }

    // Checks if a particular spear is onscreen
    private boolean isWithinBounds(Spear spear) {
        int spearX = spear.getHeadX();
        if (spearX > WindowSettings.WIDTH || spearX < 0) return false;
        int spearY = spear.getHeadY();
        if (spearY > WindowSettings.HEIGHT || spearY < 0)
            return false;
        else
            return true;
    }

    // Renders the spear thrower in the middle of the playing field
    public void render(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillOval(center.getX() - size / 2, center.getY() - size / 2, size, size);
        gc.strokeLine(center.getX(), center.getY(), end.getX(), end.getY());

        for (Spear spear : spears)
            spear.render(gc);
    }

    // Creates a new spear object and gives it the correct velocity
    public void shoot() {
        spears.add(new Spear(new Vector(center, end), getSlope()));
    }

    // Gets the slope of the line on the spear thrower which is then passed to
    // the projectile objects as its speed to keep the correct orientation
    private Vector getSlope() {
        return new Vector(new Point(), new Point(end.getX() - center.getX(), end.getY() - center.getY()));
    }

    // Sets the end of the line on the spear thrower so that it points at the
    // mouse but stays at the same radius around the spear thrower
    public void setEnd(Point mousePos) {

        double magnitude = size / 2 + size / 2 * 0.2;
        double dx = mousePos.getX() - center.getX();
        double dy = mousePos.getY() - center.getY();

        double theta = Math.atan(dy / dx);
        double mx = Math.cos(theta) * magnitude;
        double my = Math.sin(theta) * magnitude;

        if (mousePos.getX() < center.getX())
            end = new Point(center.getX() - (int) mx, center.getY() - (int) my);
        else
            end = new Point(center.getX() + (int) mx, center.getY() + (int) my);
    }

    // Checks if any spear has hit a target. Removes spear if it hits something
    public boolean checkCollision(Target target) {
        for (int i = 0; i < spears.size(); i++) {
            Spear spear = spears.get(i);
            if (Math.abs(spear.getHeadX() - target.getX()) < target.getSize() / 2) {
                if (Math.abs(spear.getHeadY() - target.getY()) < target.getSize() / 2) {
                    spears.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
}
