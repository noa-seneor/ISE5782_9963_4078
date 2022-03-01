package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Class representing a Sphere implementing Geometry
 */
public class Sphere implements Geometry{

    final Point _center;
    final double _radius;

    /**
     * Cnstructor initializing the sphere with a center Point and a radius
     * @param center : Point
     * @param radius : double
     */
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    /**
     * return normal of the sphere given a point
     * @param point
     * @return
     */
    public Vector getNormal(Point point) {

        return null;
    }

    public Point getCenter() {

        return _center;
    }

    public double getRadius() {

        return _radius;
    }

    public String toString() {
        return "Sphere: " + "center: " + _center.toString() + ", radius: " + String.valueOf(_radius);
    }
}
