package renderer;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic class extending RayTracerBase class
 */
public class RayTracerBasic extends RayTracerBase{


    private static final double DELTA = 0.1;

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
    private Color calcColor(GeoPoint p, Ray ray)
    {
        Color color =  _scene._ambientLight.getIntensity();
        color = color.add(p._geometry.getEmission()).add(calcLocalEffects(p, ray));
        return color;
    }

    /**
     * Method to check if the shapes are unshaded
     * @param gp : Geopoint
     * @param l : Vector
     * @param n : Vector
     * @return
     */
    private boolean unshaded(GeoPoint gp,LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point point = gp._point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        double maxDistance = light.getDistance(gp._point);
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
        if( intersections == null || intersections.isEmpty())
        {
            return true;
        }
        for (GeoPoint intersection : intersections)
        {
            if (intersection._point.distance(gp._point) < maxDistance)
                return false;
        }

        return true;
    }

    /**
     * Method to calculate the local effects (light) in intersection point
     * by adding diffusion & specular calculation
     * @param gp : GeoPoint
     * @param ray : Ray
     * @return color after local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp._geometry.getEmission();
        Vector v = ray.getDir().normalize();
        Vector n = gp._geometry.getNormal(gp._point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = gp._geometry.getMaterial();
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(gp._point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0)
            {
                if (unshaded(gp,lightSource,l,n)) {
                    Color iL = lightSource.getIntensity(gp._point);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }

            }
        }
        return color;
    }

    /**
     * function to calculate the diffusive factor
     * @param mat : Material
     * @param nl : double
     * @return diffusive factor
     */
    private Double3 calcDiffusive(Material mat, double nl)
    {
        return mat._kD.scale(Math.abs(nl));
    }

    /**
     * Function to calculate the specular light factor
     * @param mat : Material
     * @param n : Vector
     * @param l : Vector
     * @param nl : double
     * @param v : Vector
     * @return specular light factor
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2)).normalize();
        if(alignZero(r.dotProduct(v)) >= 0)
            return Double3.ZERO;
        return mat._kS.scale( Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), mat._nShininess));
    }


    /**
     * method to find closest intersection point between ray and scene and return it's color
     * @param ray : Ray
     * @return : Color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> lst = _scene._geometries.findGeoIntersections(ray);
        if (lst == null)
            return _scene._background;
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(lst);
        return calcColor(closestGeoPoint, ray);

    }

}
