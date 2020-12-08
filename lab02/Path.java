/**
 * A class that represents a path via pursuit curves.
 * @author Kaicheng Jia
 */

public class Path {

	public Point curr;
	public Point next;

    public Path(double x, double y) {
    	this.next = new Point(x, y);
    	this.curr = new Point();
    }

    /**
    * Returns the x-coordinate of curr
    */
    public double getCurrX() {
    	return this.curr.getX();
    }

    /**
    * Returns the y-coordinate of curr
    */
    public double getCurrY() {
    	return this.curr.getY();
    }

    /**
    * Returns the x-coordinate of next
    */
    public double getNextX() {
    	return this.next.getX();
    }

    /**
    * Returns the y-coordinate of next
    */
    public double getNextY() {
    	return this.next.getY();
    }

    /**
    * Returns curr
    */
    public Point getCurrentPoint() {
    	return curr;
    }

    /**
    * Set curr to point
    */
    public void setCurrentPoint(Point point) {
    	curr.setX(point.getX());
    	curr.setY(point.getY());
    } 

    /**
    * Sets curr to next and updates the position of next to be curr with movement defined by dx and dy.
    */
    public void iterate(double dx, double dy) {
    	curr.setX(next.getX());
    	curr.setY(next.getY());
    	next.setX(curr.getX() + dx);
    	next.setY(curr.getY() + dy);
    } 
}





