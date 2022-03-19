package geometries;


import primitives.Point;
import primitives.Vector;

/**
 * interface representing geometry shapes
 */
public interface Geometry extends Intersectable {

    Vector getNormal(Point point) ;
}
