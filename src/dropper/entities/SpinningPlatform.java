package dropper.entities;

public class SpinningPlatform extends Platform {

	public SpinningPlatform(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public SpinningPlatform(double x, double y, double width, double height, double theta) {
		super(x, y, width, height, theta);
	}
	
	public void update(){
		theta = (theta + 1) % 360;
		setVertices();
	}
}
