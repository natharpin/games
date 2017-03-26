package dropper.entities;

import java.awt.geom.Line2D;

import dropper.datastructures.Point;
import dropper.datastructures.Vector;
import dropper.window.WindowSettings;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Simple platform to bounce the ball off of, can be angled and has the groundwork setup for spinning platforms
 * @author Nathan and Andrew
 *
 */
public class Platform extends Sprite {

    protected double theta;

    protected Point vertices[];

    public Platform(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.theta = 0;
        setVertices();
    }

    public Platform(double x, double y, double width, double height, double theta) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.theta = theta >= 0 ? theta % 360 : 360 + (theta % 360);
        setVertices();
    }

    protected void setVertices() {
        vertices = new Point[4];
        double x1, x2, y1, y2;
        x1 = width * Math.cos(Math.toRadians(theta));
        y1 = width * Math.sin(Math.toRadians(theta));
        x2 = height * Math.cos(Math.toRadians(90 - theta));
        y2 = height * Math.sin(Math.toRadians(90 - theta));

        vertices[0] = new Point(x, y);
        vertices[1] = new Point(x + x1, y - y1);
        vertices[2] = new Point(x + x1 + x2, y - y1 + y2);
        vertices[3] = new Point(x + x2, y + y2);
    }

    private double[] xCoordinates() {
        double result[] = new double[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            result[i] = vertices[i].getX();
        return result;
    }

    private double[] yCoordinates() {
        double result[] = new double[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            result[i] = vertices[i].getY();
        return result;
    }

    public boolean intersects(Ball ball) {
        Point edges[] =
        {
                new Point(ball.x + ball.width / 2, ball.y), new Point(ball.x + ball.width, ball.y + ball.height / 2),
                new Point(ball.x + ball.width / 2, ball.y + ball.height), new Point(ball.x, ball.y + ball.height / 2)
        };
        for (Point bp : edges) {
            int numIntersections = 0;
            for (int i = 0; i < vertices.length; i++) {
                if (Line2D.linesIntersect(vertices[i].getX(), vertices[i].getY(), vertices[(i + 1) % 4].getX(),
                        vertices[(i + 1) % 4].getY(), bp.getX(), bp.getY(), WindowSettings.WIDTH, bp.getY()))
                    numIntersections++;
            }
            if (numIntersections % 2 != 0) {
                System.out.println("Ball intersects platform");
                return true;
            }
        }
        return false;

    }

    public void bounce(Ball ball) {
        int i = 4;
        
        double angle = theta % 90;
        int quadrant = (int) (theta / 90);
        
        double cxx = quadrant != 1 ? (width / 2) * Math.cos(Math.toRadians(angle)) : (width / 2) * Math.cos(Math.toRadians(90 - angle));
        double cxy = quadrant != 1 ? (width / 2) * Math.sin(Math.toRadians(angle)) : (width / 2) * Math.sin(Math.toRadians(90 - angle));
        double cyx = quadrant != 1 ? (height / 2) * Math.cos(Math.toRadians(90 - angle)) : (height / 2) * Math.cos(Math.toRadians(angle));
        double cyy = quadrant != 1 ? (height / 2) * Math.sin(Math.toRadians(90 - angle)) : (height / 2) * Math.sin(Math.toRadians(angle));
        
        double cx = x;
        double cy = y;
        
        switch(quadrant){
            case 0:
                cx+= cxx + cyx;
                cy+= cxy + cyy;
                break;
            case 1:
                cx+= cyx - cxx;
                cy-= cxy + cyy;
                break;
            case 2:
                cx+= (-cxx - cyx);
                cy+= (cxy - cyy);
                break;
            case 3:
                cx-= cxx - cyx;
                cy-= (-cxy - cyy);
                break;
        }

        double cbx = (ball.x + ball.width / 2) - ball.dx;
        double cby = (ball.y + ball.width / 2) - ball.dy;

        double angleToBall = (Math.toDegrees(Math.atan((cby - cy) / (cbx - cx))) - theta) % 360;

        double halfOfAngleOfWidth = Math.toDegrees(Math.atan((height / 2) / (width / 2)));
        double halfOfAngleOfHeight = 90 - halfOfAngleOfWidth;

        double topRightCorner = halfOfAngleOfHeight;
        double topLeftCorner = topRightCorner + halfOfAngleOfWidth * 2;
        double bottomLeftCorner = topLeftCorner + halfOfAngleOfHeight * 2;
        double bottomRightCorner = bottomLeftCorner + halfOfAngleOfWidth * 2;

        double negBottomRightCorner = -topRightCorner;
        double negBottomLeftCorner = -topLeftCorner;
        double negTopLeftCorner = -bottomLeftCorner;
        double negTopRightCorner = -bottomRightCorner;

        System.out.println(topRightCorner);
        System.out.println(topLeftCorner);
        System.out.println(bottomLeftCorner);
        System.out.println(bottomRightCorner);
        
        if (angleToBall >= 0) {
            if (angleToBall >= 0 && (angleToBall < topRightCorner || angleToBall > bottomLeftCorner)) {
                i = 1;
            }
            if (angleToBall >= topRightCorner && angleToBall < topLeftCorner) {
                i = 0;
            }
            if (angleToBall >= topLeftCorner && angleToBall < bottomLeftCorner) {
                i = 3;
            }
            if (angleToBall >= bottomLeftCorner && angleToBall < bottomRightCorner) {
                i = 2;
            }
        }
        else if (angleToBall < 0) {
            if (angleToBall <= 0 && (angleToBall > negBottomRightCorner || angleToBall < negTopRightCorner)) {
                i = 1;
            }
            if (angleToBall <= negBottomRightCorner && angleToBall > negBottomLeftCorner) {
                i = 2;
            }
            if (angleToBall <= negBottomLeftCorner && angleToBall > negTopLeftCorner) {
                i = 3;
            }
            if (angleToBall <= negTopLeftCorner && angleToBall > negTopRightCorner) {
                i = 0;
            }
        }
        Vector normal;
        Vector incoming = new Vector(ball.dx, ball.dy);
        normal = new Vector(vertices[(i + 1) % 4].getY() - vertices[i].getY(),
                -(vertices[(i + 1) % 4].getX() - vertices[i].getX())).normal();
        double dot = normal.dotProduct(incoming);
        Vector dotXnormal = normal.mult(dot);
        Vector dotXnormalX2 = dotXnormal.mult(1);
        Vector resultVec = incoming.add(dotXnormalX2);

        System.out.println(normal.toString());
        System.out.println(incoming.toString());
        System.out.println(dot);
        System.out.println(dotXnormal.toString());
        System.out.println(dotXnormalX2.toString());
        System.out.println(resultVec.toString());

        Point result = resultVec.end();

        System.out.println(result.toString());
        ball.dx += result.getX();
        ball.dy -= result.getY();
    }

    public void update() {
        // Stationary platform
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillPolygon(xCoordinates(), yCoordinates(), vertices.length);

        double angle = theta % 90;
        int quadrant = (int) (theta / 90);
        
        double cxx = quadrant != 1 ? (width / 2) * Math.cos(Math.toRadians(angle)) : (width / 2) * Math.cos(Math.toRadians(90 - angle));
        double cxy = quadrant != 1 ? (width / 2) * Math.sin(Math.toRadians(angle)) : (width / 2) * Math.sin(Math.toRadians(90 - angle));
        double cyx = quadrant != 1 ? (height / 2) * Math.cos(Math.toRadians(90 - angle)) : (height / 2) * Math.cos(Math.toRadians(angle));
        double cyy = quadrant != 1 ? (height / 2) * Math.sin(Math.toRadians(90 - angle)) : (height / 2) * Math.sin(Math.toRadians(angle));
        
        double cx = x;
        double cy = y;
        
        switch(quadrant){
            case 0:
                cx+= cxx + cyx;
                cy+= -cxy + cyy;
                break;
            case 1:
                cx+= cyx - cxx;
                cy-= cxy + cyy;
                break;
            case 2:
                cx+= (-cxx - cyx);
                cy+= (cxy - cyy);
                break;
            case 3:
                cx+= cxx - cyx;
                cy+= cxy + cyy;
                break;
        }
        
        gc.strokeLine(x, y, cx, cy);
        
    }
}
