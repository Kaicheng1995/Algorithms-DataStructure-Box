package bearmaps.utils.ps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testing() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX());
        System.out.println(ret.getY());
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
    }

    @Test
    public void testingPruningNearest1() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(1, 5);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(4, 4);

        KDTree mm = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point ret = mm.nearest(0, 7);
        System.out.println(ret.getX());
        System.out.println(ret.getY());

    }

    @Test
    public void testingPruningNearest2() {
        Point p1 = new Point(1, 9);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(4, 1);
        Point p4 = new Point(3, 7);
        Point p5 = new Point(5, 4);
        Point p6 = new Point(6, 8);
        Point p7 = new Point(7, 2);
        Point p8 = new Point(8, 8);
        Point p9 = new Point(7, 9);
        Point p10 = new Point(9, 6);

        KDTree mm = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        Point ret = mm.nearest(7, 4);
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}