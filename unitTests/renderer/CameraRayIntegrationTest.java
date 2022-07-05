package renderer;

import geometries.*;
import renderer.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Integration tests Class for Camera ray and Objects intersection
 */
public class CameraRayIntegrationTest {

    /**
     * function that given a camera and an intersectable object calculates:
     * the number of points intersected between the camera rays and the shape
     * function to save multiple lines of codes
     * @param camera
     * @param intersected
     * @return number of intersected points
     */
    int findIntersections(Camera camera, Intersectable intersected){
        List<Point> result = null;
        Ray ray;
        int i, j;
        for( i = 0; i < 3; i++)
            for (j = 0; j < 3; j++) {
                ray = camera.constructRay(3, 3, j, i);
                List<Point> temp=intersected.findIntersections(ray);
                if (temp!=null) {
                    if (result == null)
                        result = new LinkedList<>();
                    result.addAll(temp);
                }
            }
        if (result == null)
            return 0;
        return result.size();
    }

    /**
     * Test method for integration between Sphere and Camera
     */
    @Test
    void sphereTest(){
        Camera c1 = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);
        Camera c2 = new Camera(new Point(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);

        //TC01: camera in front of sphere with radius length 1 (2 point)
        assertEquals(2,
                findIntersections(c1,new Sphere(new Point(0,0,-3),1)),
                "expected 2 intersections");

        //TC02: max Points : camera in front of sphere with radius length 2.5 (18 point)
        assertEquals(18,
                findIntersections(c2,new Sphere(new Point(0,0,-2.5),2.5)),
                "expected 18 intersections");

        //TC03: camera in front of sphere with radius length 2 (10 point)
        assertEquals(10,
                findIntersections(c2,new Sphere(new Point(0,0,-2),2)),
                "expected 10 intersections");

        //TC04: sphere covers camera and all view plane (9 point)
        assertEquals(9,
                findIntersections(c2,new Sphere(new Point(0,0,0),4)),
                "expected 9 intersections");

        //TC05: sphere behind the camera (0 points)
        assertEquals(0,
                findIntersections(c2,new Sphere(new Point(0,0,1),0.5)),
                "no intersections");

    }

    /**
     * Test method for integration between Plane and Camera
     */
    @Test
    void planeTest(){
        Camera c1 = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);

        //TC01: Plane in front of camera (9 point)
        assertEquals(9,findIntersections(c1,new Plane(new Point(0,0,-4),new Vector(0,0,1))),
                "expected 9 intersections");

        //TC02: Plane in front and inclined to camera (9 point)
        assertEquals(9,findIntersections(c1,new Plane(new Point(0,0,-4),new Vector(0,-1,2))),
                "expected 9 intersections");

        //TC03: Plane in front and inclined to camera with less intersection (6 point)
        assertEquals(6,findIntersections(c1,new Plane(new Point(0,0,-4),new Vector(0,-1,1))),
                "expected 6 intersections");

        //TC04: plane is lying in front of camera  (0 point)
        assertEquals(0,findIntersections(c1,new Plane(new Point(0,0,-4),new Vector(0,-1,0))),
                "no intersections");
    }

    /**
     * Test method for integration between Triangle and Camera
     */
    @Test
    void triangleTest(){
        Camera c1 = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);

        //TC01: small triangle in front of camera (1 point)
        assertEquals(1,
                findIntersections(c1, new Triangle(new Point(0,1,-2),new Point(1,-1,-2), new Point(-1,-1,-2))),
                "expected 1 intersection");

        //TC02: large triangle in front of camera (2 point)
        assertEquals(2,
                findIntersections(c1, new Triangle(new Point(0,20,-2),new Point(1,-1,-2), new Point(-1,-1,-2))),
                "expected 2 intersection");

        //TC03: triangle behind camera (0 point)
        assertEquals(0,
                findIntersections(c1, new Triangle(new Point(0,20,3),new Point(1,-1,3), new Point(-1,-1,3))),
                "no intersection");

    }
}