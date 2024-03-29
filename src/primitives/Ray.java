package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Class representing a Ray
 */
public class Ray {
    private final Point _p0;
    private final Vector _dir;
    private static final double DELTA = 0.1;


    /**
     * Constructor initializing the Ray with a point and a direction vector
     * normalize the vector received in parameter
     * @param p0 : Point
     * @param dir : Vector
     */

    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    public Ray(Point p0, Vector dir, Vector normal) {

        _dir = dir.normalize();
        if (dir.dotProduct(normal) == 0) {
            _p0 = p0;
        }
        else
        {
            int sign = 1;
            if (dir.dotProduct(normal) <0)
                sign = -1;
            _p0 = p0.add(normal.scale(sign*DELTA));

        }
    }

    public Point getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    public Point getPoint(double t){
        return _p0.add(_dir.scale(t));
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return this._p0.equals(other._p0) && this._dir.equals(other._dir);
    }

    /**
     * calculates closest point to ray
     * @param lst : List<Point>
     * @return closest point to ray of the list
     */
    public Point findClosestPoint(List<Point> lst){
        /**
        double closestDist = Double.MAX_VALUE;
        Point result = null;

        for (Point p: lst)
        {
            if (_p0.distance(p) < closestDist) {
                closestDist = _p0.distance(p);
                result = p;
            }
        }
        return result;
         */
        return lst == null || lst.isEmpty() ? null
                : findClosestGeoPoint(lst.stream().map(p -> new GeoPoint(null, p)).toList())._point;


    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> lst){
        double closestDist = Double.MAX_VALUE;
        GeoPoint result = null;

        for (GeoPoint p: lst)
        {
            if (_p0.distance(p._point) < closestDist) {
                closestDist = _p0.distance(p._point);
                result = p;
            }
        }
        return result;
    }
    public String toString() {

        return "Ray{ " + "q0: " + _p0 + " ,dir: " + _dir +" }";
    }
}
