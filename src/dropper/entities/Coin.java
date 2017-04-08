package dropper.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin extends Sprite {

    private int i = 0;
    private static Image[] imageArray = new Image[]{
        new Image("pics/front.png"), new Image("pics/flip.png")
    };

    public Coin(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }

    public void update() {
        i = (i + 1) % 20;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(imageArray[i / 10], x, y);
    }

    public boolean intersects(Ball b) {
        double cx = x + width / 2;
        double cy = y + height / 2;
        double bx = b.x + b.width / 2;
        double by = b.y + b.width / 2;

        double distance = Math.sqrt(Math.pow(bx - cx, 2) + Math.pow(by - cy, 2));
        if (distance < (width / 2) + (b.width / 2)) return true;
        return false;
    }

}
