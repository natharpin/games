package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SplitPowerup extends Powerup{

    public SplitPowerup(double x, double y){
        super();
        this.x = x;
        this.y = y;
    }
    
    public void update() {
        //Do nothing. Possibly animation loop?
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(x, y, width, height);
    }

}
