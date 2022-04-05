package elements;

import primitives.Color;
import primitives.Double3;

/**
 * Class representing AmbientLight
 */
public class AmbientLight {

    Color _intensity;

    /**
     * default constructor , intensity default color set to black
     */
    public AmbientLight() {
        _intensity = Color.BLACK;
    }
    /**
     * method to reduce the intensity color
     * @param iA : Color
     * @param kA: Double3
     */
    public AmbientLight(Color iA, Double3 kA)
    {
        _intensity = iA.reduce(kA);
    }


    public Color getIntensity() {
        return _intensity;
    }

}
