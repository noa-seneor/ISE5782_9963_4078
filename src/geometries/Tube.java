package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Class representing a tube implementing geometry interface
 */
public class Tube implements Geometry {

    private Ray _axisRay;
    private double _radius;

    /**
     * Constructor initializing the Tube with a ray (axis)  and a double (radius)
     * @param axisRay : ray
     * @param radius : double
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    public Ray getAxisRay() {

        return _axisRay;
    }

    public double getRadius() {

        return _radius;
    }

    /**
     * equal method for Tube
     * @param obj
     * @return bool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Sphere)) return false;
        Tube other = (Tube) obj;
        return this._axisRay.equals(other._axisRay) && this._radius == (other._radius);
    }


    /**
     * return normal of Tube (infinite cylinder normal) given a point
     * 𝑡 = 𝑣 ∙ (𝑃 − 𝑃0)
     * 𝑂 = 𝑃0 + 𝑡 ∙ 𝑣
     * n = normalize(P - O)
     * @param point : Point
     * @return Vector
     */
    public Vector getNormal(Point point) {
        double t = _axisRay.getDir().dotProduct(point.subtract(_axisRay.getP0()));
        Point O;
        if (t==0)
            O = _axisRay.getP0();
        else
            O = _axisRay.getP0().add(_axisRay.getDir().scale(t));
        return point.subtract(O).normalize();
    }

    public String toString() {
        return "Tube{ " + "axis ray: " + _axisRay + ", radius: " + _radius + " }";
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}


