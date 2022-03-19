package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> _intersectables;

    /*
     We chose LinkedList because it is more memory efficient and has a faster runtime than ArrayList
     */
    public Geometries()
    {
        _intersectables = new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable ... list){

        add(list);
    }

    private void add(Intersectable... list)
    {

        Collections.addAll(_intersectables, list);
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable geo: _intersectables) {
            List<Point> geoPoints = geo.findIntersections(ray);
            if (geoPoints!=null){
                if (result==null)
                    result = new LinkedList<>();
                result.addAll(geoPoints);
            }
        }
        return result;
    }
}
