package src;

import java.awt.*;
import java.awt.geom.Point2D;

public class CircleObject extends CollisionObject{
    //instance variables
    final private double radius;
    final private int r = (int) (Math.random() * 256);
    final private int g = (int) (Math.random() * 256);
    final private int b = (int) (Math.random() * 256);
    Color c = new Color(r,g,b);
    public CircleObject(double xPoint, double yPoint, double radius) {
        super(xPoint, yPoint);
        this.radius = radius;
        setCenter();

    }
    private void setCenter(){
        double xPoint = get_xPoint() - radius;
        double yPoint = get_yPoint() - radius;
        set_xPoint(xPoint);
        set_yPoint(yPoint);
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        //sets circle to randomized color and draws
        g2d.setColor(c);
        g2d.fillOval((int) ((int) get_xPoint()- radius), (int) ((int) get_yPoint()- radius), (int) radius*2, (int) radius*2);
    }

    @Override
    public double computeDistance(double cameraX, double cameraY) {
        Point2D cameraPoint = new Point2D.Double(cameraX, cameraY);
        Point2D circleCenter = new Point2D.Double(this.xPoint, this.yPoint);
        double distance = circleCenter.distance(cameraPoint) - this.radius;
        return distance;
    }




}
