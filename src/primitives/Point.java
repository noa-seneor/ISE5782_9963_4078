package primitives;

/**
 * Class representing a point and it's operations functions
 */

public class Point {
    public static final Point ZERO =new Point(0,0,0) ;
    final Double3 _xyz;

    /**
     * constructor to initialize _xyz with three doubles
     * @param d1
     * @param d2
     * @param d3
     */
    public Point(double d1, double d2, double d3) {
        _xyz = new Double3(d1,d2,d3);

    }

    /**
     * Constructor to initialize _xyz with a Double3 value
     * @param xyz
     */
    public Point(Double3 xyz){
        _xyz = new Double3(xyz.d1, xyz.d2, xyz.d3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return this._xyz.equals(other._xyz) ;
    }

    @Override
    public String toString() {

        return _xyz.toString();
    }

    /***
     * Method to substract the current point _xyz with a Point p to return vector from _xyz to p
     * @param p : Point
     * @return new vector, result of substract
     */
    public Vector subtract(Point p) {
        if(p._xyz.equals(_xyz)){
            throw new IllegalArgumentException("(0,0,0) vector invalid");
        }
        return new Vector(
                _xyz.d1 - p._xyz.d1,
                _xyz.d2 - p._xyz.d2,
                _xyz.d3 - p._xyz.d3 );
    }

    /**
     * Method to add a vector to the current Point
     * @param v :  vector
     * @return result of add
     */
    public Point add(Vector v) {
        return new Point(
                v._xyz.d1 + _xyz.d1,
                v._xyz.d2 + _xyz.d2,
                v._xyz.d3 + _xyz.d3 );

    }

    public Point calculatePointBetween(Point p)
    {
      return addPoint(p).divide(2);
    }

    public Point divide(int div)
    {
        return new Point(
                 _xyz.d1/div,
               _xyz.d2/div,
                _xyz.d3/div);
    }
    public Point addPoint(Point p) {
        return new Point(
                p._xyz.d1 + _xyz.d1,
                p._xyz.d2 + _xyz.d2,
                p._xyz.d3 + _xyz.d3 );

    }

    /**
     * method to calculate the distance squared of the two points
     * @param p : Point
     * @return squared distance between the current point _xyz and p
     */
    public double distanceSquared(Point p) {
        double s1 = _xyz.d1 - p._xyz.d1;
        double s2 = _xyz.d2 - p._xyz.d2;
        double s3 = _xyz.d3 - p._xyz.d3;

        return s1*s1 + s2*s2 + s3*s3;
    }

    /**
     * method to calculates distance between two points
     * uses distancesquared method
     * @param p: Point
     * @return distance between the current point _xyz and p
     */
    public double distance(Point p) {

        return Math.sqrt(distanceSquared(p));
    }


    public double getX() {
        return _xyz.d1;
    }
    public double getY() {
        return _xyz.d2;
    }
    public double getZ() {
        return _xyz.d3;
    }
}
