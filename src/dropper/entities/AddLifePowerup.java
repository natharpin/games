package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AddLifePowerup extends Powerup{

    public AddLifePowerup(double x, double y){
        super();
        this.x = x;
        this.y = y;
    }
    
    public void render(GraphicsContext gc){
        gc.setFill(Color.GREY);
        gc.fillOval(x, y, width, height);
    }
}
