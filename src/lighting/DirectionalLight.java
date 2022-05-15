package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a Directional Light
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector _direction;

    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * get normalized direction of light
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return _direction.normalize();
    }
}
