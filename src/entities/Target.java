package entities;

import datastructures.Point;
import datastructures.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import window.WindowSettings;

public class Target {

	private int size;
	private Color color;

	private Point center;
	private Vector speed;
	
	//private int bounceCooldown = 0;

	public Target(Point center, Vector speed) {
		this.center = center;
		this.speed = speed;
		this.color = Color.BLACK;
		this.size = 30;
	}
	
	public Target(Point center, Vector speed, Color color, int size) {
        this.center = center;
        this.speed = speed;
        this.color = color;
        this.size = size;
    }
	
	public int getX(){
		return center.getX();
	}
	
	public int getY(){
		return center.getY();
	}
	
	public int getSize(){
	    return size;
	}

	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(center.getX() - size / 2, center.getY() - size / 2, size, size);
	}

	public void move() {
		//bounceCooldown--;
		Point next = new Point(center.getX() + speed.getDx(), center.getY() + speed.getDy());
		if (next.getX() + size / 2 > WindowSettings.WIDTH || next.getX() - size / 2 < 0) {
			speed.mirrorX();
		}
		if (next.getY() + size / 2 > WindowSettings.HEIGHT || next.getY() - size / 2 < 0) {
			speed.mirrorY();
		}

		/*
		if(Math.abs(next.getX() - WindowSettings.WIDTH / 2) < 30){
			if(Math.abs(next.getY() - WindowSettings.HEIGHT / 2) < 30){
				speed.mirror();
			}
		}
		*/

		center = new Point(center.getX() + speed.getDx(), center.getY() + speed.getDy());
	}
}
