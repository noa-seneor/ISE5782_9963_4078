package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class representing a Sphere implementing Geometry
 */
public class Sphere extends Geometry{

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

    /**
     * equal method for Sphere
     * @param obj
     * @return bool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Sphere)) return false;
        Sphere other = (Sphere) obj;
        return this._center.equals(other._center) && this._radius == (other._radius);
    }

    public String toString() {
        return "Sphere{ " + "center: " + _center + ", radius: " + _radius + " }";
    }


    /**
     * method to find intersection between ray and sphere
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0() ;
        Point O = _center;
        Vector V = ray.getDir();

        if (O.equals(p0))
            return List.of(new GeoPoint(this,_center.add(V.scale(_radius))));

        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm*tm);
        if (d >= _radius)
                return null;
        double th = Math.sqrt(_radius*_radius - d*d);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 > 0 && t2 > 0)
        {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return (List.of(new GeoPoint(this,p1),new GeoPoint(this,p2)));
        }

        if ( t1 > 0 ){
            Point p1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, p1));
        }

        if ( t2 > 0 ){
            Point p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this,p2));
        }
        return null;
    }
}
