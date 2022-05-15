package geometries;


import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * interface representing geometry shapes
 */
public abstract class Geometry extends Intersectable {

    protected Color _emission = Color.BLACK;
    Material _material = new Material();

    public abstract Vector getNormal(Point point) ;

    public Color getEmission()
    {
        return _emission;
    }

    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    public Material getMaterial() {

        return _material;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }
}
