package geometries;
import primitives.*;

import java.util.List;

/**
 * intersectable interface
 */
public abstract class Intersectable {

    public static class GeoPoint {
        public Geometry _geometry;
        public Point _point;

        public GeoPoint(Geometry geometry, Point point)
        {
            _geometry = geometry;
            _point = point;
        }
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint)) return false;
            GeoPoint other = (GeoPoint) obj;
            return this._geometry.equals(other._geometry) && this._point.equals(other._point);
        }

       public String tostring()
       {
           return "GeoPoint: { " + "geometry : " + _geometry + " point: " + _point + " }";
       }

    }
    /**
     * function declaration to find intersections between ray and an intersectable
     * @param ray
     * @return List of intersected points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp._point).toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    };

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
