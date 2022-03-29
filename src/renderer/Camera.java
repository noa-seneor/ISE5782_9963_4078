package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Camera Class
 */
public class Camera {
    private Point _p0;
    private Vector _vTo, _vUp, _vRight;
    private double _height, _width;
    private double _distance;

    /**
     * camera constructor
     * normalize vTo and Vup and calculates vRight normalized
     * @param p0
     * @param vTo
     * @param vUp
     */
    public Camera(Point p0, Vector vTo, Vector vUp)
    {
        _p0 = p0;
        if ( !isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("the two vectors must be orthogonal");
        _vTo = vTo.normalize();
        _vUp = vUp.normalize();
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    /**
     * set view Plane width and height
     * @param width
     * @param height
     * @return this camera
     */
    public Camera setVPSize(double width, double height){
       _width = width;
       _height = height;
       return this;
    }

    /**
     * set camera distance to viewPlane
     * @param distance
     * @return this camera
     */
    public Camera setVPDistance(double distance){
        _distance = distance;
        return this;
    }

    /**
     * @return vUp
     */
    public Vector getvUp() {
        return _vUp;
    }

    /**
     * @return vTo
     */
    public Vector getvTo() {
        return _vTo;
    }

    /**
     * @return vRight
     */
    public Vector getvRight(){
        return _vRight;
    }

    /**
     * @return distance
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * @return height
     */
    public double getHeight() {
        return _height;
    }

    /**
     * @return width
     */
    public double getWidth() {
        return _width;
    }

    /**
     * @param nX : number of columns
     * @param nY : number of rows
     * @param j  : pixel column
     * @param i  : pixel row
     * @return ray constructed through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i){

        Point Pc = _p0.add(_vTo.scale(_distance));
        double Rx = _width/nX;
        double Ry = _height/nY;

        double Yi = ( i - (nY -1)/2d)*Ry;
        double Xj = ( j - (nX-1)/2d)*Rx;

        Point Pij = Pc;
        if (!isZero(Xj))
            Pij = Pij.add(_vRight.scale(Xj));
        if (!isZero(Yi))
            Pij = Pij.add(_vUp.scale(-Yi));

        Vector Vij = Pij.subtract(_p0);

        Ray r = new Ray(_p0, Vij.normalize());
        return r;
    }

    /**
     * bonus
     * deplace camera ( by adding doubles to camera vectors)
     * @param addUp : double
     * @param addRight : double
     * @param addTo : double
     */
    public Camera moveCamera(double addUp, double addRight, double addTo) {
        if (addUp ==0 && addRight ==0 && addTo == 0)
            return this;
        if (addUp != 0)
            _p0 = _p0.add(_vUp.scale(addUp));
        if (addRight != 0)
            _p0 = _p0.add(_vRight.scale(addRight));
        if (addTo != 0)
            _p0 = _p0.add(_vTo.scale(addTo));
        return this;
    }
}