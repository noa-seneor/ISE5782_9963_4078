package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface representing light source
 */
public interface LightSource {

    public Color getIntensity(Point p);

    public Vector getL(Point p);

    public double getDistance(Point point);
}
