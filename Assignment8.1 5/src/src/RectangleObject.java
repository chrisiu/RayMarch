package src;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RectangleObject extends CollisionObject{
    //instance variables
    private double width;
    private double height;
    final private int r = (int) (Math.random() * 256);
    final private int g = (int) (Math.random() * 256);
    final private int b = (int) (Math.random() * 256);
    Color c = new Color(r,g,b);
    public RectangleObject(double xPoint, double yPoint,double width, double height) {
        super(xPoint, yPoint);
        this.width = width;
        this.height = height;
        setCenter();

    }
    private void setCenter() {
        double xPoint = get_xPoint() - width / 2;
        double yPoint = get_yPoint() - height / 2;
        set_xPoint(xPoint);
        set_yPoint(yPoint);
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        //sets rectangle to a random color and draws
        g2d.setColor(c);
        g2d.fillRect((int) ((int) get_xPoint() -width/2), (int) ((int) get_yPoint() - height/2), (int) this.width,(int) this.height);
    }

    public double computeDistance(double cameraX, double cameraY) {
        double left = xPoint - (width / 2);
        double right = xPoint + (width / 2);
        double top = yPoint - (height / 2);
        double bottom = yPoint + (height / 2);


        double minDistance = Double.MAX_VALUE;

        Line2D.Double[] sides = new Line2D.Double[4];
        sides[0] = new Line2D.Double(new Point2D.Double(left, top), new Point2D.Double(right, top));
        sides[1] = new Line2D.Double(new Point2D.Double(right, top), new Point2D.Double(right, bottom));
        sides[2] = new Line2D.Double(new Point2D.Double(right, bottom), new Point2D.Double(left, bottom));
        sides[3] = new Line2D.Double(new Point2D.Double(left, bottom), new Point2D.Double(left, top));


        for (Line2D.Double side : sides) {
            double distance = side.ptSegDist(cameraX, cameraY);
            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance;

    }
}
