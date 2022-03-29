package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Geometries class
 */
class GeometriesTest {

    /**
     * Test method for
     * {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {

        Sphere s = new Sphere(new Point(1, 1, 1), 3d);
        Triangle t = new Triangle(new Point(3,0,-1), new Point(3,3,-1),new Point(0,0,3));
        Plane p = new Plane(new Point(4,0,-2), new Point(4,4,-2),new Point(0,0,4));

        // =============== Boundary Values Tests ==================

        // **** group : geometries is empty
        // TC01: geometries list is empty
        Geometries myGeometries = new Geometries();
        assertNull(myGeometries.findIntersections(new Ray(new Point(3,0,5), new Vector(1,1,0)))
        ,"no intersection, list is empty");


        // **** Group: every shapes is in geometries list
        myGeometries.add(s, t, p);

        // TC02: No object intersected
        assertNull(myGeometries.findIntersections(new Ray(new Point(10,0,0), new Vector(1,1,1))),
                "none object intersected");

        //TC03: one intersected
        assertEquals(1,
                myGeometries.findIntersections(new Ray(new Point(0,3,0), new Vector(-1, 0, 0))).size(),
                "Only one object intersected");

        //TC04: All objects intersected
        assertEquals(3,
                myGeometries.findIntersections(new Ray(new Point(1, 0, 0),
                                new Vector(2, 2, 0))).size(),
                "All shapes are intersected");


        // =============== Equivalence Partitions Tests ==================

        //TC11: not all objects intersected
        assertEquals(2,
                myGeometries.findIntersections(new Ray(new Point(1,0,0), new Vector(1, 0, 0))).size(),
                "There are shapes intersected but not all");


    }
}