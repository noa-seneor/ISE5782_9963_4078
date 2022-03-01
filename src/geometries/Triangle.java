package geometries;

import primitives.Point;

/**
 * Class representing a Triangle extending Polygon's class
 */
public class Triangle extends Polygon{

    /**
     * Constructor initializing the Triangle with three Points
     * @param p1 : Point
     * @param p2 : Point
     * @param p3 : Point
     */
    public Triangle(Point p1, Point p2, Point p3) {

        super(p1,p2,p3);
    }

    public String toString() {
        return "Triangle: " + "p1: " + vertices.get(0).toString() + ", p2: " + vertices.get(1).toString() + ", p3: " + vertices.get(3).toString();
    }
}
