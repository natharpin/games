package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SplitPowerup extends Powerup{

	private int i = 0;
	private Image[] imageArray = new Image[2];
	
    public SplitPowerup(double x, double y){
        super();
        this.x = x;
        this.y = y;
    }
    
    public void update() {
        //Do nothing. Possibly animation loop?
    	i=(i+1)%20;
    }

    public void render(GraphicsContext gc) {
//        gc.setFill(Color.GREEN);
//        gc.fillOval(x, y, width, height);
    	imageArray[0] = new Image("pics/addBall.png");
		imageArray[1] = new Image("pics/addBallSide.png");
    	gc.drawImage(imageArray[i/10], this.x, this.y);
    }

}
