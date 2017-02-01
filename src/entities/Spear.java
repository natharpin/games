package entities;

import datastructures.Vector;
import javafx.scene.canvas.GraphicsContext;

public class Spear {

	private Vector shaft;
	private Vector speed;
	
	public Spear(Vector shaft, Vector speed){
		this.shaft = shaft;
		this.speed = speed;
	}
	
	public void move(){
		shaft.add(speed);
		shaft.addOrigin(speed);
	}
	
	public void render(GraphicsContext gc){
		gc.strokeLine(shaft.getOrigin().getX(), shaft.getOrigin().getY(), shaft.getEnd().getX(), shaft.getEnd().getY());
	}
	
	public int getHeadX(){
		return shaft.getEnd().getX();
	}
	
	public int getHeadY(){
		return shaft.getEnd().getY();
	}
}
