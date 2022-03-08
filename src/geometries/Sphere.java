package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Class representing a Sphere implementing Geometry
 */
public class Sphere implements Geometry{

    private final Point _center;
    private final double _radius;

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

        return (point.subtract(_center)).normalize();
    }

    public Point getCenter() {

        return _center;
    }

    public double getRadius() {

        return _radius;
    }

    public String toString() {
        return "Sphere{ " + "center: " + _center + ", radius: " + _radius + " }";
    }
}
