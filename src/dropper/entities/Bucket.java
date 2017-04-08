package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Simple bucket that collects the ball and has an associated score value
 * @author Nathan and Andrew
 *
 */
public class Bucket extends Sprite {

	public int score;
	
	public Bucket(double x, double  y, double width, double height, int score){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.score = score;
	}
	
	public void update() {
		//Stationary bucket
	}

	public void render(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillRect(x, y, width, height);
		gc.setFill(Color.BLACK);
		gc.fillRect(x + 3, y + 3, width - 3, 15);
	}
	
	public boolean intersects(Ball b){
		double bx = b.x + b.width / 2;
		double by = b.y + b.width / 2;
		
		if(b.x > x && b.x < x + width){
			if(b.y > y)
				return true;
		}
		return false;
	}

}
