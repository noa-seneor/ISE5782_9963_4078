package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for RayTracerBase
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     * constructor to initalize the scene
     * @param scene : Scene
     */
    public RayTracerBase(Scene scene)
    {
        _scene = scene;
    }

    /**
     * methods that receive a ray and return Color
     * @param ray : Ray
     * @return Color
     */
    public abstract Color traceRay(Ray ray);

    public abstract Color traceRaySS(Point p0, Point p1, Point p2, Point p3, Point cameraP0, int level);

    }
