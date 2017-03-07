package dropper.entities;

import dropper.datastructures.Level;
import dropper.datastructures.Point;
import dropper.window.WindowSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Controls the area above the level that drops the ball and all the ball collisions
 * @author Nathan and Andrew
 *
 */
public class DropperArea extends Sprite {
	
	private Ball inBox, inFreefall;
	private int bounceCooldown;
	
	public DropperArea(){
		this.x = 0;
		this.y = 0;
		this.width = 10;
		this.bounceCooldown = 0;
	}
	
	public void click(){
		if(inFreefall == null) inFreefall = new Ball(followBallX(), followBallY());
	}
	
	public void mouseMove(double x, double y){
		this.x = x - this.width / 2;
		this.y = y - this.width / 2;
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
		if(this.y < 0) return 0;
		return min(this.y, WindowSettings.HEIGHT / 8 - this.width);
	}
	
	private double followBallX(){
		if(this.x < 0) return 0;
		if(this.x > WindowSettings.WIDTH - this.width) return WindowSettings.WIDTH - this.width;
		return this.x;
	}
	
	public int checkCollisions(Level level){
		int score = 0;
		if(inFreefall == null) return score;
		for(Platform p : level.platforms){
			if(p.intersects(inFreefall.next()) && bounceCooldown < 0){
				bounceCooldown = 2;
				p.bounce(inFreefall);
			}
		}
		for(Bucket b : level.buckets){
			if(inFreefall == null) return score;
			if(b.intersects(inFreefall.next())){
				inFreefall = null;
				score+= b.score;
			}
		}
		return score;
	}
	
	public void update(){
		bounceCooldown--;
		if(inFreefall != null) {
			inFreefall.update();
			if(inFreefall.y > WindowSettings.HEIGHT) inFreefall = null;
			if(inFreefall != null && (inFreefall.x < 0 || inFreefall.x > WindowSettings.WIDTH)) inFreefall = null;
		}
		inBox = new Ball(followBallX(), followBallY());
	}
	
	public void render(GraphicsContext gc){
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, WindowSettings.WIDTH, WindowSettings.HEIGHT / 8);
		if(inBox != null) inBox.render(gc);
		if(inFreefall != null) inFreefall.render(gc);
	}
}
