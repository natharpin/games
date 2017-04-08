package dropper.entities;

import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {

    protected double x, y, dx, dy;
    protected double width, height;
    protected BoundingBox box;

    public abstract void update();
    public abstract void render(GraphicsContext gc);
    
}
