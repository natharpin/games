package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ball extends Sprite{
	
    private Image image = new Image("pics/ball.png");
    
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
    	gc.drawImage(image, this.x, this.y);
    }
    
}
