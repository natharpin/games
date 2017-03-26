package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Coin extends Sprite {

	public Coin(double x, double y, double size){
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
	}
	
	public void update() {
		//Stationary coin, will add animation loop later
	}

	public void render(GraphicsContext gc) {
		gc.setFill(Color.YELLOW);
		gc.fillOval(x, y, width, height);
    	gc.setStroke(Color.BLACK);
    	gc.strokeOval(x, y, width, width);
	}

	public boolean intersects(Ball b){
		double cx = x + width / 2;
		double cy = y + height / 2;
		double bx = b.x + b.width / 2;
		double by = b.y + b.width / 2;
		
		double distance = Math.sqrt(Math.pow(bx - cx, 2) + Math.pow(by - cy, 2));
		if(distance < (width / 2) + (b.width / 2))
			return true;
		return false;
	}
	
}
