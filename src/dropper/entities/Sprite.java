package dropper.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {

    protected double x, y, dx, dy;
    protected double sizeX, sizeY;

    public abstract void update();
    public abstract void render(GraphicsContext gc);
    
}
