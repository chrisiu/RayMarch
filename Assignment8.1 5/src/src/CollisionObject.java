package src;

/**
 * Abstract class that describes objects that have collision
 */
public abstract class CollisionObject implements Drawable {
    //instance variables
    public double xPoint;
    public double yPoint;

    public CollisionObject(double xPoint, double yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    /**
     * Getter for xPoint
     * @return xPoint
     */
    public double get_xPoint() {
        return xPoint;
    }

    /**
     * Sets xPoint to the given argument
     * @param xPoint
     */
    public void set_xPoint(double xPoint) {
        this.xPoint = xPoint;
    }

    /**
     * Getter for yPoint
     * @return yPoint
     */
    public double get_yPoint() {
        return yPoint;
    }

    /**
     * setter for yPoint
     * @param yPoint
     */
    public void set_yPoint(double yPoint) {
        this.yPoint = yPoint;
    }


    public abstract double computeDistance(double x, double y);

    public double getY() {
        return yPoint;
    }
    public double getX() {
        return xPoint;
    }


}
