package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static primitives.Util.*;


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
        return "Triangle{ " + "p1: " + vertices.get(0) + ", p2: " + vertices.get(1) + ", p3: " + vertices.get(3) + " }";
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p = plane.findIntersections(ray).get(0);
        Point p0 = ray.getP0();
        Vector V = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();

        double d1 = alignZero(V.dotProduct(n1));
        double d2 = alignZero(V.dotProduct(n2));
        double d3 = alignZero(V.dotProduct(n3));

        if ( isZero(d1) || isZero(d2) || isZero(d3))
            return null;
        if ( d1*d2 >0 && d2*d3 >0)
            return List.of(p);
        return null;

    }
}
