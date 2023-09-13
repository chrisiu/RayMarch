package src;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class March implements Drawable {
    private Point2D start;
    private Point2D end;
    private double radius;

    private Camera camera;



    public March(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
        camera = new Camera(0,0,10);

    }

    public Point2D getStart() {
        return start;
    }


    public Point2D getEnd() {
        return end;
    }



    public void drawObject(Graphics2D g2d) {
        g2d.setColor(Color.black);

        g2d.draw(new Line2D.Double(this.start, this.end));

        double radius = this.start.distance(this.end);
        double x = this.start.getX() - radius;
        double y = this.start.getY() - radius;
        g2d.draw(new Ellipse2D.Double(x, y, radius * 2.0, radius * 2.0));
    }

}
