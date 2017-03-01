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
        vertices[0] = new Point(x, y);
        double x1 = Math.cos(theta) * sizeX;
        double y1 = Math.sin(theta) * sizeX;
        vertices[1] = new Point(x + x1, y + y1);
        double x2 = Math.cos(90 - theta) * sizeY;
        double y2 = Math.sin(90 - theta) * sizeY;
        vertices[2] = new Point(x + x1 + x2, y + y1 + y2);
        vertices[3] = new Point(x + x2, y + y2);
        
        for(Point p : vertices){
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }
        System.out.println();
	}
	
	public void update() {
		//Stationary platform
	}

	public void render(GraphicsContext gc) {		
		gc.save();
		gc.setFill(Color.BLUE);
        Affine move = new Affine();

        move.appendTranslation(x, y);
        move.appendRotation(theta);
        move.appendTranslation(-sizeX / 2, -sizeY / 2);

        gc.setTransform(move);
        gc.fillRect(0, 0, sizeX, sizeY);
        gc.restore();
	}
}
