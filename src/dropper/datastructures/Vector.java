package dropper.datastructures;

public class Vector {

	private double endX, endY;
	
	public Vector(double endX, double endY){
		this.endX = endX;
		this.endY = endY;
	}
	
	public Point end(){
		return new Point(endX, endY);
	}
	
	public Vector add(double scalar){
		return new Vector(endX + scalar, endY + scalar);
	}
	
	public Vector mult(double scalar){
		return new Vector(endX * scalar, endY * scalar);
	}
	
	public double dotProduct(Vector dot){
		return endX * dot.endX + endY * dot.endY;
	}
	
	public Vector add(Vector vec){
		return new Vector(endX + vec.endX, endY + vec.endY);
	}
	
	public Vector normal(){
		return new Vector(endX / magnitude(), endY / magnitude());
	}
	
	public double dist(double x, double y){
		return (x * endX) + (y * endY) - magnitude();
	}
	
	public double magnitude(){
		return Math.sqrt(endX * endX + endY * endY);
	}
	
	public String toString(){
		return "(" + endX + ", " + endY + ")";
	}
}
