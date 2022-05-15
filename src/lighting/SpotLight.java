package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;


/**
 * Class represnting a Spot Light source
 */
public class SpotLight extends PointLight{
    private Vector _direction;

    protected SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalize();
    }

    /**
     * Get light intensity in a given point
     * @param p : Point
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        double m = Math.max(0, _direction.dotProduct(getL(p)));
        if (Util.isZero(m))
        {
            return Color.BLACK;
        }

        return super.getIntensity(p).scale(m);

    }

    @Override
    public Vector getL(Point p)
    {
        return p.subtract(_position).normalize();
    }
}
