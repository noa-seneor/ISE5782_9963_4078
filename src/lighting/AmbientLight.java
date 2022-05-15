package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Class representing AmbientLight
 */
public class AmbientLight extends Light{


    /**
     * default constructor , intensity default color set to black
     */
    public AmbientLight() {

        super(Color.BLACK);
    }
    /**
     * method to reduce the intensity color
     * @param iA : Color
     * @param kA: Double3
     */
    public AmbientLight(Color iA, Double3 kA)
    {

        super(iA.scale(kA));
    }


}
