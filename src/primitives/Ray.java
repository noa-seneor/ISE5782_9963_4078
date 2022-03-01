package primitives;

/**
 * Class representing a Ray
 */
public class Ray {
    final Point _q0;
    final Vector _dir;

    /**
     * Constructor initializing the Ray with a point and a direction vector
     * normalize the vector received in parameter
     * @param q0 : Point
     * @param dir : Vector
     */
    public Ray(Point q0, Vector dir) {
        _q0 = q0;
        _dir = dir.normalize();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return this._q0.equals(other._q0) && this._dir.equals(other._dir);
    }

    public String toString() {
        return "Ray: " + "q0 " + _q0.toString() + "dir " + _dir.toString();
    }
}
