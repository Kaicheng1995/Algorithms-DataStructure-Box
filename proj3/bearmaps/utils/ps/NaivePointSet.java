package bearmaps.utils.ps;



import java.util.List;

public class NaivePointSet implements PointSet{
    List<Point> allPoints;

    public NaivePointSet(List<Point> points) {
        allPoints = points;
    }

    public Point nearest(double x, double y) {
        Point newP = new Point(x, y);
        double shortest = -1;
        Point toReturn = null;
        for (Point tempP : allPoints) {
            double distance = Point.distance(newP, tempP);
            if (shortest == -1) {
                shortest = distance;
                toReturn = tempP;
            } else if (distance < shortest) {
                shortest = distance;
                toReturn = tempP;
            }
        }
        return toReturn;
    }

}
