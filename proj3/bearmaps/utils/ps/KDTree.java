package bearmaps.utils.ps;

import java.util.ArrayList;
import java.util.List;

public class KDTree {
    Node root;

    public KDTree(List<Point> points) {
        if (root == null) {
            root = new Node(points.get(0), "x");
        }
        for (int i = 1; i < points.size(); i++) {
            root.insert(points.get(i));
        }

    }

    //TO DO
    public Point nearest(double x, double y) {
        Node n = nearestHelper(root, new Point(x, y), root);
        return new Point(n.x, n.y);

    }

    private static Node nearestHelper(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        Point thisNodePoint = new Point(n.x, n.y);
        Point bestNodePoint = new Point(best.x, best.y);
        if (Point.distance(thisNodePoint, goal) < Point.distance(bestNodePoint, goal)) {
            best = n;
        }
        Node goodSide;
        Node badSide;
        if (n.splitJudge.equals("x")) {
            if (goal.getX() < n.x) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            if (goal.getY() < n.y) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        }
        best = nearestHelper(goodSide, goal, best);

        Point bestTemp = new Point(best.x, best.y);
        double diff;
        if (n.splitJudge.equals("x")) {
            if (n.x > goal.getX()) {
                diff = n.x - goal.getX();
            } else {
                diff = goal.getX() - n.x;
            }
        } else {
            if (n.y > goal.getY()) {
                diff = n.y - goal.getY();
            } else {
                diff = goal.getY() - n.y;
            }
        }
        if (Math.sqrt(Point.distance(bestTemp, goal)) > diff) {
            best = nearestHelper(badSide, goal, best);
        }



        return best;

    }

    private static class Node {
        double x;
        double y;
        Node left;
        Node right;
        String splitJudge;

        public Node(Point point, String judge) {
            x = point.getX();
            y = point.getY();
            left = null;
            right = null;
            splitJudge = judge;
        }

        public Node(Point point, Node _left, Node _right, String judge) {
            x = point.getX();
            y = point.getY();
            left = _left;
            right = _right;
            splitJudge = judge;
        }

        public void insert(Point p) {
            double xValue = p.getX();
            double yValue = p.getY();
            if (this.splitJudge.equals("x")) {
                if (xValue < this.x) {
                    if (this.left == null) {
                        this.left = new Node(p, "y");
                    } else {
                        this.left.insert(p);
                    }
                }
                if (xValue >= this.x) {
                    if (this.right == null) {
                        this.right = new Node(p, "y");
                    } else {
                        this.right.insert(p);
                    }
                }
            } else {
                if (yValue < this.y) {
                    if (this.left == null) {
                        this.left = new Node(p, "x");
                    } else {
                        this.left.insert(p);
                    }
                }
                if (yValue >= this.y) {
                    if (this.right == null) {
                        this.right = new Node(p, "x");
                    } else {
                        this.right.insert(p);
                    }
                }
            }
        }
    }
}
