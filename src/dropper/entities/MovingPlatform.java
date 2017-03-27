package dropper.entities;

import dropper.datastructures.Point;

public class MovingPlatform extends Platform {

    private Point maxDistance, distanceMoved;
    private int state = 0; //0 is initial and 1 is reverse

    public MovingPlatform(double x, double y, double width, double height, double theta, Point maxDistance, Point speed) {
        super(x, y, width, height, theta);
        this.dx = speed.getX();
        this.dy = speed.getY();
        this.maxDistance = maxDistance;
        this.distanceMoved = new Point(0, 0);
    }
    
    public void update(){
        if(state == 0){
            x+= dx;
            y+= dy;
            distanceMoved = new Point(distanceMoved.getX() + dx, distanceMoved.getY() + dy);
            if(distanceMoved.getX() > maxDistance.getX() || distanceMoved.getY() > maxDistance.getY())
                state = 1;
        }
        else {
            x-= dx;
            y-= dy;
            distanceMoved = new Point(distanceMoved.getX() - dx, distanceMoved.getY() - dy);
            if(distanceMoved.getX() < 0 || distanceMoved.getY() < 0)
                state = 0;
        }
        setVertices();
    }
}
