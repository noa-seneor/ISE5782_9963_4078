package geometries;


import primitives.Point;
import primitives.Vector;

/**
 * interface representing geometry shapes
 */
public interface Geometry {

    Vector getNormal(Point point) ;
}
