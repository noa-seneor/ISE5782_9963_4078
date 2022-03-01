package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Class representing Cylinder extending Tube
 */
public class Cylinder extends Tube {

    double _height;

    /**
     * Constructor initializing the cylinder with a ray, double , double
     * calls the super constructor Tube initializing aisRay and radius
     * @param axisRay : Ray
     * @param radius : double
     * @param height : double
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay,radius);
        _height = height;
    }

    /**
     * return normal of the Cylinder given a point
     * @param point
     * @return normal
     */
    public Vector getNormal(Point point) {

        return null;
    }

    public double getHeight() {

        return _height;
    }

    public String toString() {
        return "Cylinder: " + "axis ray: " + _axisRay.toString() + ", radius: " + String.valueOf(_radius) + " ,height: " + String.valueOf(_height);
    }
}
