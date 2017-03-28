package dropper.entities;

import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.animation.Interpolator;
import javafx.animation.Transition;

public class Coin extends Sprite {
	
	private int i = 0;
	private Image[] imageArray = new Image[2];
	
	public Coin(double x, double y, double size){
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		imageArray[0] = new Image("pics/front.png");
		imageArray[1] = new Image("pics/flip.png");		
	}
	
	public void update() {
		i=(i+1)%20;
	}

	public void render(GraphicsContext gc) {
    	gc.drawImage(imageArray[i/10], x, y);
	}

	public void flipCoin(){
		
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
