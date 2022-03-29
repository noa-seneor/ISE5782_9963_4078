package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

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

        if (p1.equals(p2) || p2.equals(p3) || p1.equals(p3))
            throw new IllegalArgumentException("error, same points");
        _q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        _normal = (v1.crossProduct(v2)).normalize();
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

        return "Plane{ " + "q0: " + _q0 + ", normal: " + _normal + " }";
    }

    /**
     * equal method for Plane
     * @param obj
     * @return bool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Sphere)) return false;
        Plane other = (Plane) obj;
        return this._q0.equals(other._q0) && this._normal == (other._normal);
    }



    /**
     * method to find intersection between ray and plane
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector V = ray.getDir();
        Vector N = getNormal();
        if (_q0.equals(p0))
            return null;
        double nQMinusP0 = N.dotProduct(_q0.subtract(p0));
        double NV = N.dotProduct(V);
        if (isZero(NV))
            return null;
        double t = alignZero(nQMinusP0/NV);
        if ( t > 0){
            Point P = ray.getPoint(t);
            return List.of(P);
        }
        return null;
    }
}
