package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point,primitives.Point,primitives.Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: test for correct plane
        try {
            new Plane(
                    new Point(0, 0, 1),
                    new Point(1, 0, 0),
                    new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

        // =============== Boundary Values Tests ==================

        // TC11: test for plane with two same points

        assertThrows(IllegalArgumentException.class,
                () -> new Plane(
                        new Point(0, 0, 1),
                        new Point(0, 0, 1),
                        new Point(0, 1, 0)),
                "constructed Plane with same points");

        // TC12: test for plane with points on same line

        assertThrows(IllegalArgumentException.class,
                () -> new Plane(
                        new Point(0, 0, 1),
                        new Point(0, 0, 2),
                        new Point(0, 0, 3)),
                "constructed Plane with points on same line");


    }

    @Test
    void testGetNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: There is a simple single test here
            Plane pl = new Plane(
                    new Point(0, 0, 1),
                    new Point(1, 0, 0),
                    new Point(0, 1, 0));
            double sqrt3 = Math.sqrt(1d / 3);
            assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to Plane");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections(){

        Plane plane = new Plane(new Point(1,1,0), new Vector(0,0,1));

        // ============ Equivalence Partitions Tests ==============
        //TC01: ray intersects the plane
        assertEquals( List.of(new Point(1,0,0)),
                plane.findIntersections(new Ray(new Point(0,0,-1), new Vector(1,0,1))),"bad intersection point");

        //TC02: ray does'nt intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "there's no intersection");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        //TC11: ray included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,1,1), new Vector(0, 0, 1))),
                 "there's no intersection");
        //TC12: ray not included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,1,1), new Vector(0,1,0))),
                "parralel ray not included");

        // **** Group: Ray is orthogonal to the plane
        //TC13: ray before the plane
        assertEquals(List.of(new Point(1,1,0)), plane.findIntersections(new Ray(new Point(1,1,-1), new Vector(0,0,1))),
                "ray orthogonal and before the plane");
        //TC14: ray in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,1,0), new Vector(0,0,1))),
                "ray orthogonal and in the plane");
        //TC15: ray after the plane
        assertEquals(List.of(new Point(1,1,0)),
                plane.findIntersections(new Ray(new Point(1,1,1), new Vector(0,0,-1))),
                "ray orthogonal and after the plane");


        //TC16: ray neither orthogonal nor parallel and begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0.25, 0.25), new Vector(1, 1, 0))),
                "ray neither orthogonal nor parallel and begins at the plane");

        //TC17: ray neither orthogonal nor parallel to the plane and begins in reference point
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0))),
                "ray neither orthogonal nor parallel and begin in reference point");


    }
}