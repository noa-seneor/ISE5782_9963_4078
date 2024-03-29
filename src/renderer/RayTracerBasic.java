package renderer;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic class extending RayTracerBase class
 */
public class RayTracerBasic extends RayTracerBase {


    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;


    /**
     * constructor to initalize the scene
     *
     * @param scene : Scene
     */
    public RayTracerBasic(Scene scene) {

        super(scene);
    }


    /**
     * method to return color trough a given point
     *
     * @param p : point
     * @return : color
     */
    protected Color calcColor(GeoPoint p, Ray ray) {
        return calcColor(p, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K))
                .add(_scene._ambientLight.getIntensity());
    }

    /**
     * return color trough given Point with global & local effects
     * @param p
     * @param ray
     * @param level
     * @param k
     * @return
     */
    protected Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
        Color color = p._geometry.getEmission();
        color = color.add(calcLocalEffects(p, ray, k));
        return level == 1 ? color : color.add(calcGlobalEffects(p, ray.getDir(), level, k));
    }

    /**
     * Function to check the transparency of an objet
     *
     * @param geoPoint    : GeoPoint
     * @param light : LightSource
     * @param l     : Vector direction
     * @param n:    Vector
     * @return
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource light, Vector l, Vector n) {

        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint._point, lightDirection, n);
        double lightDistance = light.getDistance(geoPoint._point);
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);

        Double3 ktr = new Double3(1.0);
        if (intersections == null)
            return ktr;

        intersections.removeIf((item) ->
        {
            double distance = alignZero(item._point.distance(geoPoint._point));
            return distance >= lightDistance;
        });

        if (intersections.isEmpty())
            return ktr;

        for (GeoPoint gp : intersections) {
            ktr = ktr.product(gp._geometry.getMaterial()._kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }

        return ktr;
    }


    /**
     * UNUSED
     * Method to check if the shapes are unshaded
     *
     * @param p : Geopoint
     * @param l : Vector
     * @param n : Vector
     * @return
     */
    private boolean unshaded(GeoPoint p, LightSource light, Vector l, Vector n) {

        Vector lightDirection = l.scale(-1);
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Ray lightRay = new Ray(p._point.add(delta), lightDirection, n);
        double maxDistance = light.getDistance(p._point);

        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null || intersections.isEmpty()) {
            return true;
        }
        for (GeoPoint gp : intersections) {
            if (gp._point.distance(gp._point) < maxDistance)
                return false;
        }

        return true;
    }

    /**
     * Method to calculate the local effects (light) in intersection point
     * by adding diffusion & specular calculation
     *
     * @param gp  : GeoPoint
     * @param ray : Ray
     * @return color after local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp._geometry.getEmission();
        Vector v = ray.getDir().normalize();
        Vector n = gp._geometry.getNormal(gp._point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = gp._geometry.getMaterial();
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(gp._point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K) & !ktr.product(k).equals(new Double3(MIN_CALC_COLOR_K))) {
                    Color iL = lightSource.getIntensity(gp._point).scale(ktr);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }

            }
        }
        return color;
    }


    /**
     * Function to calculate global effect of point, if the ray reflected/refracted/both
     *
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp._geometry.getNormal(gp._point);
        Material material = gp._geometry.getMaterial();

        Double3 kkr = material._kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K) & !kkr.equals(new Double3(MIN_CALC_COLOR_K))) // the ray is reflected
            color = calcGlobalEffect(constructReflectedRay(gp._point, v, n), level, material._kR, kkr);

        Double3 kkt = material._kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K) & !kkt.equals(new Double3(MIN_CALC_COLOR_K))) // the ray is refracted
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp._point, v, n), level, material._kT, kkt));
        return color;
    }

    /**
     * Return final color with the global effects
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene._background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * Construct Refracted Ray
     */
    Ray constructRefractedRay(Point p, Vector v, Vector n) {
        return new Ray(p, v, n); // r
    }

    /**
     * Construct Reflected Ray
     *
     * @param p
     * @param v
     * @param n
     * @return
     */
    Ray constructReflectedRay(Point p, Vector v, Vector n) {
        v = v.normalize();
        double vn = v.dotProduct(n);
        if (Util.isZero(vn))
            return null;
        return new Ray(p, v.subtract(n.scale(2d * vn)).normalize(), n);
    }

    /**
     * Find closest intersection within a given ray
     *
     * @param ray
     * @return
     */
    public GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionPoints = _scene._geometries.findGeoIntersections(ray);
        if (intersectionPoints == null) {
            return null;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionPoints);
        return closestPoint;
    }


    /**
     * function to calculate the diffusive factor
     *
     * @param mat : Material
     * @param nl  : double
     * @return diffusive factor
     */
    private Double3 calcDiffusive(Material mat, double nl) {
        return mat._kD.scale(Math.abs(nl));
    }

    /**
     * Function to calculate the specular light factor
     *
     * @param mat : Material
     * @param n   : Vector
     * @param l   : Vector
     * @param nl  : double
     * @param v   : Vector
     * @return specular light factor
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2)).normalize();
        if (alignZero(r.dotProduct(v)) >= 0)
            return Double3.ZERO;
        return mat._kS.scale(Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), mat._nShininess));
    }


    /**
     * method to find closest intersection point between ray and scene and return it's color
     *
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


    /**
     * Super Sampling
     * function that return the average color of the pixel recursively
     * @param p0 corner upLeft
     * @param p1 corner upRight
     * @param p2 corner downRight
     * @param p3 corner downLeft
     * @param cameraP0 camera position
     * @param level level of the recursion
     * @return the average of the colors of the rays
     */
    @Override
    public Color traceRaySS(Point p0, Point p1, Point p2, Point p3, Point cameraP0, int level) {
        List<Color> listColors=new ArrayList<>();
        List<Point> listOfCorners=List.of(p0, p1, p2, p3);
        Ray ray;

        for(int i=0; i<4;i++){
            ray=new Ray(cameraP0, listOfCorners.get(i).subtract(cameraP0).normalize());
            Intersectable.GeoPoint gp= findClosestIntersection(ray);
            if(gp!=null)
                listColors.add(calcColor(gp,ray));
            else
                listColors.add(_scene._background);
        }
        // condition for stopping the recursion
        if(level==0 || (listColors.get(0).equalsDelta(listColors.get(1)) && listColors.get(0).equalsDelta(listColors.get(2))
                && listColors.get(0).equalsDelta(listColors.get(3)) && listColors.get(2).equalsDelta(listColors.get(3))
                && listColors.get(1).equalsDelta(listColors.get(3))
                && listColors.get(1).equalsDelta(listColors.get(2))))
            return listColors.get(0);

        Point midUp=findMiddlePoint(p0, p1);
        Point midDown=findMiddlePoint(p2, p3);
        Point midRight=findMiddlePoint(p2, p1);
        Point midLeft=findMiddlePoint(p0, p3);
        Point center=findMiddlePoint(midRight, midLeft);

        // recursive call, call the function 4 times (corners square)
        return traceRaySS(p0, midUp, center, midLeft, cameraP0, level-1).scale(0.25)
                .add(traceRaySS(midUp, p1, midRight, center, cameraP0, level-1).scale(0.25))
                .add(traceRaySS(center, midRight, p2, midDown, cameraP0, level-1).scale(0.25))
                .add(traceRaySS(midLeft, center, midDown, p3, cameraP0, level-1).scale(0.25));
    }

    /**
     * help function to find the middle point between 2 points
     * @param p1 point1
     * @param p2 point2
     * @return
     */
    public Point findMiddlePoint(Point p1, Point p2){
        double x= (p1.getX()+ p2.getX())/2d;
        double y= (p1.getY()+ p2.getY())/2d;
        double z= (p1.getZ()+ p2.getZ())/2d;
        return new Point(x, y, z);
    }

}
