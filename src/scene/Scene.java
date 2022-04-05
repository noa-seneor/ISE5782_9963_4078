package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Class implementing a scene
 */
public class Scene {

    public String _name;
    public Color _background;
    public AmbientLight _ambientLight;
    public Geometries _geometries;


    /**
     * constructor that receives only name , and initialize the rest by default
     * @param name : String
     */
    public Scene(String name){
        _name = name;
        _background = Color.BLACK;
        _ambientLight = new AmbientLight();
        _geometries = new Geometries();
    }

    public Scene setBackground(Color background) {
        _background = background;
        return  this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return  this;
    }

}
