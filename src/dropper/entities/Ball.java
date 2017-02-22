package dropper.entities;

import javafx.scene.canvas.GraphicsContext;

public class Ball extends Sprite{

    public Ball(double x, double y, double dx, double dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
    
    public void update(){}
    public void move(){}
    public void render(GraphicsContext gc){}
    
}
