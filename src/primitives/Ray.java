package primitives;

/**
 * Class representing a Ray
 */
public class Ray {
    private final Point _p0;
    private final Vector _dir;

    /**
     * Constructor initializing the Ray with a point and a direction vector
     * normalize the vector received in parameter
     * @param p0 : Point
     * @param dir : Vector
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    public Point getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    public Point getPoint(double t){
        return _p0.add(_dir.scale(t));
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return this._p0.equals(other._p0) && this._dir.equals(other._dir);
    }

    public String toString() {

        return "Ray{ " + "q0: " + _p0 + " ,dir: " + _dir +" }";
    }
}
