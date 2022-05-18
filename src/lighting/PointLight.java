package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing Point Light
 */
public class PointLight extends Light implements LightSource{

    protected Point _position;
    private double _kC =1;
    private  double _kL = 0;
    private double _kQ = 0;

    protected PointLight(Color intensity, Point position) {
        super(intensity);
        _position = position;
    }

    /**
     * get point light intensity Given a Point
     * @param p : Light position Point
     * @return Color after calculating the light intensity
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(_position);
        double d2 = p.distanceSquared(_position);
        Color I0 = super.getIntensity();
        return I0.reduce(_kC + _kL*d + _kQ*d2);

    }

    @Override
    public Vector getL(Point p) {

        return p.subtract(_position).normalize();
    }

    public PointLight setKc(double kC)
    {
        _kC = kC;
        return this;
    }

    public PointLight setKl(double kL)
    {
        _kL = kL;
        return this;
    }

    public PointLight setKq(double kQ)
    {
        _kQ = kQ;
        return this;
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(_position);
    }
}
