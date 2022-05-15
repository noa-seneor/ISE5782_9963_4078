package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class implementing a scene
 */
public class Scene {

    public String _name;
    public Color _background;
    public AmbientLight _ambientLight;
    public Geometries _geometries;
    public List<LightSource> _lights = new LinkedList<>();


    /**
     * set lights of the scene
     * @param lst
     * @return
     */
    public Scene setLights(LightSource ... lst)
    {
        Collections.addAll(_lights, lst);
        return this;
    }

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

    /**
     * set background color
     * @param background
     * @return
     */
    public Scene setBackground(Color background) {
        _background = background;
        return  this;
    }

    /**
     * set scene's ambient light
     * @param ambientLight
     * @return
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
        return this;
    }

    /**
     * set geometries of the scene
     * @param geometries
     * @return
     */
    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return  this;
    }

}
