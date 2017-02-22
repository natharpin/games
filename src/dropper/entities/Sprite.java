package dropper.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {

    protected double x, y, dx, dy;

    public abstract void update();
    public abstract void move();
    public abstract void render(GraphicsContext gc);
    
}
