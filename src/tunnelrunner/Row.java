package tunnelrunner;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.util.Duration;

public class Row {

    Group group = new Group();
    ArrayList<Platform> platforms = new ArrayList<Platform>();
    boolean[] showing = new boolean[5];
    Random rng = new Random();
    
    public Row(double z, int difficulty, PhongMaterial material, Group root){
        for(int i = 0; i < showing.length; i++){
            showing[i] = true;
        }
        for(int i = 0; i < difficulty; i++){
            boolean done = false;
            while(!done){
                int attempt = rng.nextInt(5);
                if(showing[attempt]){
                    showing[attempt] = false;
                    done = true;
                }
            }
        }
        for(int i = 0; i < 5; i++){
            if(!showing[i]){
                continue;
            }
            Platform platform = new Platform(-300 + (i * 150), 30, z, material);
            platforms.add(platform);
            group.getChildren().add(platform.box);
            TranslateTransition tr = new TranslateTransition(Duration.millis(10000), platform.box);
            tr.setByZ(-1000);
            tr.setCycleCount(1);
            tr.setOnFinished(event -> {root.getChildren().remove(platform.box);});
            tr.play();
        }        
    }
}
