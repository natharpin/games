package spears.datastructures;

/*
 * Simple vector class to represent a vector on a plane
 */
public class Vector {

	private Point origin, end;
	
	public Vector(Point origin, Point end){
		this.origin = origin;
		this.end = end;
	}
	
	public Point getOrigin(){
		return origin;
	}
	
	public Point getEnd(){
		return end;
	}
	
	// Mirrors the vector on the origin
	public void mirror(){
		end = new Point(-end.getX(), -end.getY());
	}
	
	// Negates the x coordinate of the end point
	public void mirrorX(){
		end = new Point(-end.getX(), end.getY());
	}
	
	   // Negates the y coordinate of the end point
	public void mirrorY(){
		end = new Point(end.getX(), -end.getY());
	}
	
	public int getDx(){
		return end.getX() - origin.getX();
	}
	
	public int getDy(){
		return end.getY() - origin.getY();
	}
	
	// Adds a vector to this vector
	public void add(Vector vec){
		end = new Point(end.getX() + vec.getDx(), end.getY() + vec.getDy());
	}
	
	// Adds a vector to the origin
	public void addOrigin(Vector vec){
		origin = new Point(origin.getX() + vec.getDx(), origin.getY() + vec.getDy());
	}
	
}
