package renderer;

import primitives.Color;
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
}
