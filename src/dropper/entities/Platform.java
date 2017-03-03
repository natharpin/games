package dropper.entities;

import dropper.datastructures.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Platform extends Sprite {

	private double theta;
	
	private Point vertices[];
	
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
		
		this.vertices = new Point[4];
		setVertices();
	}

	private void setVertices(){
	    double x1, x2, y1, y2;
        x1 = sizeX / 2 * Math.cos(Math.toRadians(theta));
        y1 = sizeX / 2 * Math.sin(Math.toRadians(theta));
        x2 = sizeY / 2 * Math.cos(Math.toRadians(90 - theta));
        y2 = sizeY / 2 * Math.sin(Math.toRadians(90 - theta));
	    
        vertices[0] = new Point(x + x1 + x2, y + y1 + y2);
        vertices[1] = new Point(x + x1 - 2 * x2, y + y1 - 2 * y2);
        vertices[2] = new Point(x  - 2 * x1 - 2 * x2, y - 2 * y1 - 2 * y2);
        vertices[3] = new Point(x - 2 * x1 + x2, y - 2 * y1 + y2);
        
        for(Point p : vertices){
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }
        System.out.println();
	}
	
	private double[] xCoordinates(){
	    double result[] = new double[vertices.length];
	    for(int i = 0; i < vertices.length; i++)
	        result[i] = vertices[i].getX();
	    return result;
	}
	
   private double[] yCoordinates(){
        double result[] = new double[vertices.length];
        for(int i = 0; i < vertices.length; i++)
            result[i] = vertices[i].getY();
        return result;
    }
	
	public void update() {
		//Stationary platform
	}

	public void render(GraphicsContext gc) {	
	    gc.setFill(Color.BLUE);
		gc.fillPolygon(xCoordinates(), yCoordinates(), vertices.length);
	}
}
