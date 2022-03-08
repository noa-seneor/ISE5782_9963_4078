package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Class representing Cylinder extending Tube
 */
public class Cylinder extends Tube {

    private double _height;

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

    public double getHeight() {

        return _height;
    }

    /**
     * return normal of the Cylinder given a point
     * @param p : Point
     * @return normal
     */
    public Vector getNormal(Point p) {

        // calculate end base point P1
        Point P1 = (getAxisRay().getP0()).add(getAxisRay().getDir().scale(_height));

        // if point on the starting base
        if (p.distance(getAxisRay().getP0()) <= getRadius())
            return getAxisRay().getDir().scale(-1);

        //if point on the ending base
        else if (p.distance(P1) <= getRadius())
            return getAxisRay().getDir();

        // if point on the side
        else
            return super.getNormal(p);

    }

    @Override
    public String toString() {
        Ray axis = getAxisRay();
        return "Cylinder{ " + "axis ray: " + axis + ", radius: " + super.getRadius() + " ,height: " + _height +" }";
    }
}
