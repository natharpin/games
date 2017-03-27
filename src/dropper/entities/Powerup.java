package dropper.entities;

import javafx.scene.canvas.GraphicsContext;

public class Powerup extends Sprite{

    public Powerup(){
        width = 20;
        height = 20;
    }
    
    public void update() { }

    public void render(GraphicsContext gc) { }
    
    public boolean intersects(Ball b){
        double cx = x + width / 2;
        double cy = y + height / 2;
        double bx = b.x + b.width / 2;
        double by = b.y + b.height / 2;
        if(Math.sqrt((cx - bx) * (cx - bx) + (cy - by) * (cy - by)) <= width / 2 + b.width / 2)
            return true;
        return false;
    }

}
