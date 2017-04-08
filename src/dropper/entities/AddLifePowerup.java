package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AddLifePowerup extends Powerup{

	private int i = 0;
	private Image[] imageArray = new Image[2];
	
    public AddLifePowerup(double x, double y){
        super();
        this.x = x;
        this.y = y;
    }
    
    public void update() {
		//Stationary coin, will add animation loop later
		i=(i+1)%20;
	}
    
    public void render(GraphicsContext gc){
//        gc.setFill(Color.GREY);
//        gc.fillOval(x, y, width, height);
    	imageArray[0] = new Image("pics/addLife.png");
		imageArray[1] = new Image("pics/addLifeSide.png");
    	gc.drawImage(imageArray[i/10], x, y);
    }
}
