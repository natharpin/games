package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Sprite{
	
    public Ball(double x, double y){
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.width = 10;
    }
	
    public Ball(double x, double y, double dx, double dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = 10;
    }
    
    public Ball(double x, double y, double dx, double dy, double size){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = size;
    }
    
    public Ball next(){
    	return new Ball(x + dx, y + dy, dx, dy, width);
    }
    
    public void update(){
    	x+= dx;
    	y+=dy;
    	dy+=.2;
    }
    
    public void render(GraphicsContext gc){
    	gc.setFill(Color.RED);
    	gc.fillOval(this.x, this.y, this.width, this.width);
    	
    	gc.strokeLine(x, y + width / 2, x + dx, y + width / 2 + dy);
    	gc.strokeLine(x + width, y + width / 2, x + width + dx, y + width / 2 + dy);
    	gc.strokeLine(x + width / 2, y, x + width / 2 + dx, y + dy);
    	gc.strokeLine(x + width / 2, y + width, x + width / 2 + dx, y + width + dy);
    }
    
}
