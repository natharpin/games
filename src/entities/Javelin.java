package entities;

import java.util.ArrayList;

import datastructures.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import window.WindowSettings;

public class Javelin {

	private int size = 30;
	
	private ArrayList<Spear> spears;
	private Point center, end;
	
	public Javelin(Point center, Point end){
		this.center = center;
		this.end = end;
		spears = new ArrayList<Spear>();
	}
	
	public void update(){
		purge();
		for(Spear spear : spears)
			spear.move();
	}
	
	public void purge(){
		for(int i = 0; i < spears.size(); i++){
			Spear spear = spears.get(i);
			if(!isWithinBounds(spear))
				spears.remove(i);
		}
	}
	
	private boolean isWithinBounds(Spear spear){
		int spearX = spear.getHeadX();
		if(spearX > WindowSettings.WIDTH || spearX < 0)
			return false;
		int spearY = spear.getHeadY();
		if(spearY > WindowSettings.HEIGHT || spearY < 0)
			return false;
		else return true;
	}
	
	public void render(GraphicsContext gc){
		gc.setFill(Color.GRAY);
		gc.fillOval(center.getX() - size / 2, center.getY() - size / 2, size, size);
		gc.strokeLine(center.getX(), center.getY(), end.getX(), end.getY());
		
		for(Spear spear: spears)
			spear.render(gc);
	}
	
	public void shoot(){
		spears.add(new Spear(new Vector(center, end), getSlope()));
	}
	
	private Vector getSlope(){
		return new Vector(new Point(), new Point(end.getX() - center.getX(), end.getY() - center.getY()));
	}
	
	public void setEnd(Point mousePos){

		double magnitude = size / 2 + size / 2 * 0.2;
		double dx = mousePos.getX() - center.getX();
		double dy = mousePos.getY() - center.getY();
		
		double theta = Math.atan(dy / dx);
		double mx = Math.cos(theta) * magnitude;
		double my = Math.sin(theta) * magnitude;
		
		if(mousePos.getX() < center.getX())
				end = new Point(center.getX() - (int)mx, center.getY() - (int)my);
		else
			end = new Point(center.getX() + (int)mx, center.getY() + (int)my);
	}
	
	public boolean checkCollision(Target target){
		for(int i = 0; i < spears.size(); i++){
			Spear spear = spears.get(i);
			if(Math.abs(spear.getHeadX() - target.getX()) < 15){
				if(Math.abs(spear.getHeadY() - target.getY()) < 15){
					spears.remove(i);
					return true;
				}
			}
		}
		return false;
	}
}
