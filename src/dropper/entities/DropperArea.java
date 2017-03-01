package dropper.entities;

import dropper.window.WindowSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DropperArea extends Sprite {
	
	private Ball inBox, inFreeFall;
	
	public DropperArea(){
		this.x = 0;
		this.y = 0;
		this.sizeX = 10;
	}
	
	public void click(){
		if(inFreeFall == null) inFreeFall = new Ball(this.x, this.y);
	}
	
	public void mouseMove(double x, double y){
		this.x = x - this.sizeX / 2;
		this.y = y - this.sizeX / 2;
	}
	
	private double min(double a, double b){
		if(a < b) return a;
		return b;
	}
	
	private double max(double a, double b){
		if(a > b) return a;
		return b;
	}
	
	private double followBallY(){
		if(this.y < this.sizeX / 2) return 0;
		return min(this.y, WindowSettings.HEIGHT / 8 - this.sizeX);
	}
	
	private double followBallX(){
		if(this.x - (this.sizeX / 2) < 0) return 0;
		if(this.x > WindowSettings.WIDTH - this.sizeX) return WindowSettings.WIDTH - this.sizeX;
		return this.x;
	}
	
	public void update(){
		if(inFreeFall != null) {
			inFreeFall.update();
			if(inFreeFall.y > WindowSettings.HEIGHT) inFreeFall = null;
		}
		inBox = new Ball(followBallX(), followBallY());
	}
	
	public void render(GraphicsContext gc){
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT / 8);
		if(inBox != null) inBox.render(gc);
		if(inFreeFall != null) inFreeFall.render(gc);
	}
}
