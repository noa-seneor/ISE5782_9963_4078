package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class implementing Intersectable
 */
public class Geometries implements Intersectable {
    private List<Intersectable> _intersectables;

    /**
     * default constructor
     We chose LinkedList because it is more memory efficient and has a faster runtime than ArrayList
     */
    public Geometries()
    {

        _intersectables = new LinkedList<Intersectable>();
    }

    /**
     * constructor
     * @param list: list of intersectables
     */
    public Geometries(Intersectable ... list){

        add(list);
    }

    /**
     * add to _intersectables list the objects in list
     * @param list of intersectables
     */
    public void add(Intersectable... list)
    {

        Collections.addAll(_intersectables, list);
    }

    /**
     * Method to find intersection between all
     * objects in _intersectables list and a given ray
     * @param ray
     * @return List of Point intersected
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable geo: _intersectables) {
            List<Point> geoPoints = geo.findIntersections(ray);
            if (geoPoints!=null){
                if(result== null){
                    result= new LinkedList<>();
                }
                result.addAll(geoPoints);
            }
        }
        return result;
    }
}
