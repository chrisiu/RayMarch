package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.geom.Point2D;


import javax.swing.JPanel;

/**
 * Displays and updates the logic for the top-down raymarcher.
 */
public class RaymarcherPanel extends JPanel {
    private ArrayList<CollisionObject> shapeList = new ArrayList<>();
    private Camera camera;
    /**
     * We need to keep a reference to the parent swing app for sizing and
     * other bookkeeping.
     */
    private final RaymarcherRunner raymarcherRunner;

    public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
        this.raymarcherRunner = raymarcherRunner;
        this.setPreferredSize(new Dimension(this.raymarcherRunner.getFrame().getWidth(),
                this.raymarcherRunner.getFrame().getHeight()));
        generateRandomShapes();
        camera = new Camera(0,0,10);
        addMouseMotionListener(camera);
        addMouseListener(camera);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GRAY);
        this.drawFromList(g2d);
        this.camera.drawObject(g2d);
        double min = Double.MAX_VALUE;
        for (CollisionObject obj : shapeList) {
            double distance = obj.computeDistance(camera.getX(), camera.getY());
            if (distance < min) {
                min = distance;
            }
        }
        int circleRadius = (int) (min);
        double circleX = camera.getX() - circleRadius;
        double circleY = camera.getY() - circleRadius;


        ArrayList<March> marches = march();


        for (March march : marches) {
            march.drawObject(g2d);
        }




    }






    private void generateRandomShapes() {
        for (int i = 0; i < 16; i++) {
            double randomY = Math.random() * (this.getPreferredSize().height - 100); //program crashes my GPU if width or
            double randomX = Math.random() * (this.getPreferredSize().width - 100); //height is too big
            if (i%2 == 0) {
                double randomWidth =  Math.random() * 200;
                double randomHeight = Math.random() * 200;
                RectangleObject randomRectangle = new RectangleObject(randomX, randomY, randomWidth, randomHeight);
                shapeList.add(randomRectangle);
            } else {
                double randomRadius = Math.random() * 100;
                CircleObject randomCircle = new CircleObject(randomX, randomY, randomRadius);
                shapeList.add(randomCircle);
            }

        }
    }
    private void drawFromList(Graphics2D g2d){
        for (CollisionObject c: shapeList){
            c.drawObject(g2d);

        }
    }



    public ArrayList<March> march() {
        double threshold = 0.01;
        ArrayList<March> marches = new ArrayList<>();
        ArrayList<CollisionObject> objects = this.shapeList;
        Point2D currentIterationPoint = new Point2D.Double(camera.getX() , camera.getY());
        boolean withinThreshold;

        do {
            double minDistance = Double.MAX_VALUE;
            CollisionObject closestObject = null;

            for (CollisionObject object : objects) {
                double distance = object.computeDistance(currentIterationPoint.getX(), currentIterationPoint.getY());
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }

            withinThreshold = minDistance < threshold;

            if (!withinThreshold) {
                Point2D endPoint = new Point2D.Double(currentIterationPoint.getX() + minDistance * Math.cos(camera.getAngle()), currentIterationPoint.getY()  + minDistance * Math.sin(camera.getAngle()));
                March currentMarch = new March(currentIterationPoint, endPoint);
                marches.add(currentMarch);
                currentIterationPoint = endPoint;


                if (currentIterationPoint.getX() > 1280 || currentIterationPoint.getY() > 640 || currentIterationPoint.getX() < 0 || currentIterationPoint.getY() < 0) {
                    break;
                }
            }
        } while (!withinThreshold);


        return marches;
    }


}


