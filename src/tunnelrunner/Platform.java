package tunnelrunner;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Platform {
    
    public Box box;
    
    public Platform(double transX, double transY, double transZ, PhongMaterial material){
        box = new Box(100, 10, 200);
        box.setMaterial(material);
        box.setTranslateX(transX);
        box.setTranslateY(transY);
        box.setTranslateZ(transZ);
    }
    
}
