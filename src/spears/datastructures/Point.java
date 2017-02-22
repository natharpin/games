package spears.datastructures;

/*
 * Simple point class that holds two integer values, representing coordinate values on a plane
 */
public class Point {

	private int x, y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(){
		x = 0;
		y = 0;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	// Moves a point by the values given
	public void move(int dx, int dy){
		x+= dx;
		y+= dy;
	}
	
}
