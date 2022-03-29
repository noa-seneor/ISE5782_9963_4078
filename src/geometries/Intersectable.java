package geometries;
import primitives.*;

import java.util.List;

/**
 * intersectable interface
 */
public interface Intersectable {

    /**
     * function declaration to find intersections between ray and an intersectable
     * @param ray
     * @return List of intersected points
     */
    public List<Point> findIntersections(Ray ray);
}
