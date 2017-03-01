package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Sprite{
	
    public Ball(double x, double y){
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.sizeX = 10;
    }
	
    public Ball(double x, double y, double dx, double dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.sizeX = 10;
    }
    
    public Ball(double x, double y, double dx, double dy, double size){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.sizeX = size;
    }
    
    public void update(){
    	x+= dx;
    	y+=dy;
    	dy+=1;
    }
    
    public void render(GraphicsContext gc){
    	gc.setFill(Color.RED);
    	gc.fillOval(this.x, this.y, this.sizeX, this.sizeX);
    }
    
}
