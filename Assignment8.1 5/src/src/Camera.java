package src;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Camera implements Drawable, MouseMotionListener, MouseListener {
    //instance variables
    private double x;
    private double y;
    private double radius;

    private double angle;

    public Camera(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;


    }


    @Override
    public void drawObject(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillOval((int) ((int) x-radius/2), (int) ((int) y-radius/2),(int) radius,(int) radius);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
    public double getAngle() {
        return angle;
    }
    public void increaseAngle() {
        this.angle += Math.PI / 4;
    }


    public void decreaseAngle() {
        this.angle -= Math.PI / 4;
    }


    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.increaseAngle();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            this.decreaseAngle();
        }


    }


    public void mousePressed(MouseEvent e) {
    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseExited(MouseEvent e) {
    }
}


