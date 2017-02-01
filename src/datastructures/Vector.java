package datastructures;

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
	
	public void mirror(){
		end = new Point(-end.getX(), -end.getY());
	}
	
	public void mirrorX(){
		end = new Point(-end.getX(), end.getY());
	}
	
	public void mirrorY(){
		end = new Point(end.getX(), -end.getY());
	}
	
	public int getDx(){
		return end.getX() - origin.getX();
	}
	
	public int getDy(){
		return end.getY() - origin.getY();
	}
	
	public void add(Vector vec){
		end = new Point(end.getX() + vec.getDx(), end.getY() + vec.getDy());
	}
	
	public void addOrigin(Vector vec){
		origin = new Point(origin.getX() + vec.getDx(), origin.getY() + vec.getDy());
	}
	
}
