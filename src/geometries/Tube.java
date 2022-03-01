package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Class representing a tube implementing geometry interface
 */
public class Tube implements Geometry {

    Ray _axisRay;
    double _radius;

    /**
     * Constructor initializing the Tube with a ray (axis)  and a double (radius)
     * @param axisRay : ray
     * @param radius : double
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * return normal of the tube given a point
     * @param point
     * @return
     */
    public Vector getNormal(Point point) {

        return null;
    }

    public Ray getAxisRay() {

        return _axisRay;
    }

    public double getRadius() {

        return _radius;
    }

    public String toString() {
        return "Tube: " + "axis ray: " + _axisRay.toString() + ", radius: " + String.valueOf(_radius);
    }
}


