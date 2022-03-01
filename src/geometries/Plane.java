package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Class representing a Plane implementing Geometry
 */
public class Plane implements Geometry{

    final Point _q0;
    final Vector _normal;

    /**
     * Constrcutor that initializes the Plane with a point and a normal vector
     * normalizes the vector received in parameter
     * @param q0 : Point
     * @param normal : vector
     */
    public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalize();
    }

    /**
     * Constructor initializing the Plane with three points
     * choose a representing point between the three points
     * @param p1 : Point
     * @param p2 : point
     * @param p3 : Point
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        _normal = null;
    }
    public Point getq0() {

        return _q0;
    }

    /**
     * return normal of the Plane given a point
     * @param p : Point
     * @return normal of plane
     */
    public Vector getNormal(Point p) {

        return _normal;
    }

    /**
     * return normal of the Plane
     * @return normal
     */
    public Vector getNormal() {

        return _normal;
    }

    public String toString() {
        return "Plane: " + "q0: " + _q0.toString() + ", normal: " + _normal.toString();
    }
}
