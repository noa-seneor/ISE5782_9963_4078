package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.lang.StrictMath.random;
import static primitives.Color.BLACK;
import static primitives.Util.isZero;

/**
 * Camera Class
 */
public class Camera {

    private Point _p0;

    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;

    private double _height, _width;
    private double _distance;

    private ImageWriter _imageWriter;
    private RayTracerBase _rayTracer;

    int _antiAliasing = 0;
    int _superSampling =0;
    int threadsCount = 0;
    private static final int SPARE_THREADS = 2;


    /**
     * Set multi-threading <br>
     * - if the parameter is 0 - number of cores less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Camera setMultithreading(int threads) {
        if (threads < -2)
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if (threads >= -1)
            this.threadsCount = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            this.threadsCount = cores <= 2 ? 1 : cores;
        }
        return this;
    }

    /**
     * camera constructor
     * normalize vTo and Vup and calculates vRight normalized
     * @param p0 : Point
     * @param vTo : toward Vector
     * @param vUp : up Vector
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
     * @param width : double
     * @param height : double
     * @return this camera
     */
    public Camera setVPSize(double width, double height){
       _width = width;
       _height = height;
       return this;
    }

    /**
     * set camera distance to viewPlane
     * @param distance : double
     * @return this camera
     */
    public Camera setVPDistance(double distance){
        _distance = distance;
        return this;
    }

    /**
     * set ray tracer
     *@param rayTracer : RayTracerBase
     *@return this camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        _rayTracer = rayTracer;
        return this;
    }

    /**
     * set ImageWriter
     * @param imageWriter : ImageWriter
     * @return this : Camera
     */
    public Camera setImageWriter(ImageWriter imageWriter)
    {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     * set antiaAliasing (number of rays)
     * @param antiAliasing : int
     * @return this: camera
     */
    public Camera setAntiAliasing(int antiAliasing) {
        if (antiAliasing <0)
            throw new IllegalArgumentException("Antialiasing rays can't be less than 0");
        _antiAliasing = antiAliasing;
        return this;
    }

    /**
     * set superSampling ( 0 or less no super sampling)
     * @param superSampling ( level of recursion)
     * @return
     */
    public Camera setsuperSampling(int superSampling) {
        if (_superSampling <0)
            throw new IllegalArgumentException("Antialiasing rays can't be less than 0");
        _superSampling = superSampling;
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
     * Construct Random rays through one pixel
     * @param nX : number of columns
     * @param nY : number of rows
     * @param j  : current column
     * @param i  : current row
     * @return
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i){

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


        LinkedList<Ray> rays = new LinkedList<>();
        rays.add(r);

        double randX, randY;

        Point point;
        for (int p=0; p<_antiAliasing; ++p)
        {
            randX = ThreadLocalRandom.current().nextDouble(-Rx/2, Rx/2);
            randY = ThreadLocalRandom.current().nextDouble(-Ry/2, Ry/2);

            point = new Point(Pij.getX() + randX, Pij.getY() + randY , Pij.getZ());
            rays.add(new Ray(_p0,point.subtract(_p0)));

        }
        return rays;
    }


    /**
     * for supersampling
     * This function receive the pixel position and size and return a list of all the
     * corner Points of the Pixel
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public List<Point> constructCorners(int nX, int nY, int j, int i){

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

        double deltaX = 0.01*Rx;
        double deltaY = 0.01*Ry;


        List<Point> points = new ArrayList<>();
        points.add(new Point(Pij.getX() + -Rx/2, Pij.getY() + Ry/2 , Pij.getZ()));
        points.add(new Point(Pij.getX() + Rx/2, Pij.getY() + Ry/2 , Pij.getZ()));
        points.add(new Point(Pij.getX() + Rx/2, Pij.getY() + -Ry/2 , Pij.getZ()));
        points.add(new Point(Pij.getX() + -Rx/2, Pij.getY() + -Ry/2 , Pij.getZ()));

        return points;

    }


    /**
     * Construct center ray through pixel
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        Point Pc = _p0.add(_vTo.scale(_distance));

        double Rx = _width / nX;
        double Ry = _height / nY;

        double Yi = (i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;

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


    /**
     * renderImage method, construct ray through every pixel, and write it with its color
     */
    /*
    public void renderImage() {
            if (_imageWriter == null)
                throw new MissingResourceException("missing", ImageWriter.class.getName(), "in renderImage");
            if (_rayTracer == null)
                throw new MissingResourceException("missing", RayTracerBase.class.getName(), "in renderImage");

            try{
                int count = 0;
                int nX = _imageWriter.getNx();
                int nY = _imageWriter.getNy();
                for (int i = 0; i < nX; i++) {
                    for (int j = 0; j < nY; j++) {
                        Color color = BLACK;
                            List<Ray> rays = constructRays(nX, nY, j, i);
                            for (Ray r : rays)
                                color = color.add(_rayTracer.traceRay(r));
                            _imageWriter.writePixel(j, i, color.reduce(rays.size()));

                    }

            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("unsupported" + e.getClassName());
        }
    }
    */

    /**
     * renderImage method, construct ray through every pixel, and write it with its color
     */
    public void renderImage() {
        if (_imageWriter == null)
            throw new MissingResourceException("missing", ImageWriter.class.getName(), "in renderImage");
        if (_rayTracer == null)
            throw new MissingResourceException("missing", RayTracerBase.class.getName(), "in renderImage");

        try{
            int count = 0;
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            if (_superSampling==0) {
                for (int i = 0; i < nX; i++) {
                    for (int j = 0; j < nY; j++) {
                        castMultiRays(nX, nY, i, j);

                    }

                }
            }
            else
            {
                for (int i = 0; i < nX; i++) {
                    for (int j = 0; j < nY; j++) {
                        castSSrays(nX, nY, i, j);

                    }

                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("unsupported" + e.getClassName());
        }
    }


    /** Render image with threads
     * renderImage method, construct ray through every pixel, and write it with its color
     */
    public Camera renderImageMT() {
        if (_imageWriter == null)
            throw new MissingResourceException("missing", ImageWriter.class.getName(), "in renderImage");
        if (_rayTracer == null)
            throw new MissingResourceException("missing", RayTracerBase.class.getName(), "in renderImage");

        int nY = _imageWriter.getNy();
        int nX = _imageWriter.getNx();

        Pixel.initialize(nY, nX, 1);
        if (threadsCount == 0) {
            for (int i = 0; i < nY; ++i)
                for (int j = 0; j < nX; ++j) {
                    castMultiRays(nX, nY, j, i);
                    Pixel.pixelDone();
                    Pixel.printPixel();
                }
        } else if (threadsCount == -1) {
            IntStream.range(0, nY).parallel().forEach(i -> IntStream.range(0, nX).parallel().forEach(j -> {
                castMultiRays(nX, nY, j, i);
                Pixel.pixelDone();
                Pixel.printPixel();
            }));
        } else {
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    if(_superSampling<1) {
                        for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone()) {
                            castMultiRays(nX, nY, pixel.col, pixel.row);
                        }
                    }
                    else
                    {
                        for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone()) {
                            castSSrays(nX, nY, pixel.col, pixel.row);
                        }
                    }
                }).start();
            }
            Pixel.waitToFinish();
        }
        return this;
    }


    /**
     * print grid method
     * @param interval : int
     * @param color : Color
     */
    public void printGrid(int interval, Color color) {
        if (_imageWriter == null)
            throw new MissingResourceException("missing", ImageWriter.class.getName(), "in printGrid");

        int nX = _imageWriter.getNx();
        int nY= _imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     * Cast 1 ray from camera to color the pixel
     *
     * @param nX  number of pixels in row
     * @param nY  number of pixels in column
     * @param i pixel index in row
     * @param j pixel index in column
     */
    private void castRay(int nX, int nY, int i, int j)
    {
        Ray r = constructRay(nX,nY,i,j);
        Color color = _rayTracer.traceRay(r);
        _imageWriter.writePixel(i,j,color);
    }

    /**
     * Super Sampling Casting
     * Cast multiple rays from camera to color the pixel
     *
     * @param nX  number of pixels in row
     * @param nY  number of pixels in column
     * @param i pixel index in row
     * @param j pixel index in column
     */
    private void castSSrays(int nX, int nY, int i, int j)
    {
        Ray r = constructRay(nX,nY,i,j);
        List<Point> corners = constructCorners(nX,nY,i,j);
        Color color = _rayTracer.traceRaySS(corners.get(0), corners.get(1)
                ,corners.get(2), corners.get(3), _p0, _superSampling);
        _imageWriter.writePixel(i,j,color);
    }

    /**
     * Cast 1 ray or multiples rays(if Anti-aliasing) from camera to color the pixel
     *
     * @param nX  number of pixels in row
     * @param nY  number of pixels in column
     * @param i pixel index in row
     * @param j pixel index in column
     */
    private void castMultiRays(int nX, int nY, int i, int j)
    {
        Color color = BLACK;
        List<Ray> rays = constructRays(nX, nY, i,j);


        for (Ray r : rays) {
            color = color.add(_rayTracer.traceRay(r));
        }
        _imageWriter.writePixel(i,j, color.reduce(rays.size()));

    }
    /**
     * call writeToImage method of imagewriter
     */
    public void writeToImage() {
            if (_imageWriter == null)
                throw new MissingResourceException("missing", ImageWriter.class.getName(), "in writeToImage");
            _imageWriter.writeToImage();
        }



}

