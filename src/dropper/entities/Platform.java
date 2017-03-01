package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform extends Sprite {

	public double theta;
	
	public Platform(double x, double y, double sizeX, double sizeY) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public Platform(double x, double y, double sizeX, double sizeY, double theta) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.theta = theta;
	}

	public void update() {
		//Stationary platform
	}

	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillRect(this.x, this.y, this.sizeX, this.sizeY);
	}
}
