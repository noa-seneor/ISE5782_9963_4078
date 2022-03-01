package primitives;
import static  primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class representing a vector extending Point Class
 */
public class Vector extends Point{

    /**
     * Constructor to initialize the vector with three double
     * call's the super constructor of Point
     * @param x :double
     * @param y :double
     * @param z :double
     */
    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if (_xyz.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("Vector (0,0,0) invalid");
        }
    }

    /**
     * constructor to initialize the vector with a Double3 parameter
     * calls the super constructor of Point
     * @param d : Double3
     */
    public Vector(Double3 d)
    {
        super(d.d1,d.d2,d.d3);
        if (_xyz.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("Vector (0,0,0) invalid");
        }
    }

    /**
     * Method to calculate the addition of two vectors
     * uses the add function of Double3
     * @param v :  vector
     * @return result of the addition of the current vector with vector v
     */
    public Vector add(Vector v) {
        return new Vector(_xyz.add(v._xyz));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return this._xyz.equals(other._xyz);
    }

    /**
     * Method to calculate the squared length of the current vector
     * @return the squared length as a double
     */
    public double lengthSquared() {
        return _xyz.d1 * _xyz.d1 + _xyz.d2* _xyz.d2 + _xyz.d3* _xyz.d3;
    }

    /**
     *Cartesian Length of the vector
     * uses lengthsquared() function
     * @return the length as double
     **/
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * scalar product of the two vector
     * uses Double3 product function
     * @param other the second vector
     * @return resulting scalar
     */
    public double dotProduct(Vector other)
    {
        Double3 d = _xyz.product(other._xyz);
        return d.d1 +d.d2 +d.d3;


    }

    /**
     * check if the two vectors are parallel if yes throw exception
     * cross product of the two vector
     * @param other the second vector
     * @return resulting cross product
     **/
    public Vector crossProduct(Vector other) {
        double u1 = _xyz.d1;
        double u2 = _xyz.d2;
        double u3 = _xyz.d3;

        double v1 = other._xyz.d1;
        double v2 = other._xyz.d2;
        double v3 = other._xyz.d3;

        if ( u1/v1 == u2/v2 &&  u1/v1 == u3/v3 ) {
            throw new IllegalArgumentException("the two vectors are parallel");
        }
        return new Vector(
                u2*v3 -u3*v2,
                u3*v1 - u1*v3,
                u1*v2 - u2*v1 );

    }

    /**
     * Normalization of the vector
     * @return normalized vector
     */

    public Vector normalize() {
        double size = alignZero(length());
        return new Vector(
                _xyz.d1 / size,
                _xyz.d2 / size,
                _xyz.d3 / size) ;
    }

    /**
     * multiplication of current vector with a scalar
     * uses Double3 scale() function
     * @param scalar
     * @return result of the multiplication
     */
    public Vector scale(double scalar) {
        return new Vector(_xyz.scale(scalar));
    }

    @Override
    public String toString() {
        return "Vector" + _xyz.toString();
    }
}
