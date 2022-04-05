package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * RayTracerBasic class extending RayTracerBase class
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * constructor to initalize the scene
     * @param scene : Scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * method to return color trough a given point
     * @param p : point
     * @return : color
     */
    private Color calcColor(Point p)
    {
        return _scene._ambientLight.getIntensity();
    }

    /**
     * method to find closest intersection point between ray and scene and return it's color
     * @param ray : Ray
     * @return : Color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> lst = _scene._geometries.findIntersections(ray);
        if (lst == null)
            return _scene._background;
        Point closestPoint = ray.findClosestPoint(lst);
        return calcColor(closestPoint);

    }

}
