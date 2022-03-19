package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon t = new Triangle(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), t.getNormal(new Point(0, 0, 1)), "Bad normal to trinagle");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections(){

        Triangle t = new Triangle(new Point(-1,0,0), new Point(1,0,0),new Point(0,0,2));
        // ============ Equivalence Partitions Tests ==============
        //TC01: inside Triangle
        assertEquals(List.of(new Point(0,0,1)),t.findIntersections(new Ray(new Point(0,1,0), new Vector(0,-1,1))),
                "bad intersection point");

        //TC02:outside against edge
        assertNull(t.findIntersections(new Ray(new Point(-1,1,1), new Vector(0,-1,0))),
                "no intersection point");

        //TC03:outside against vertex
        assertNull(t.findIntersections(new Ray(new Point(-2,1,-1), new Vector(0,-1,0))),
                "no intersection point");

        // =============== Boundary Values Tests ==================

        //TC14:on edge
        assertNull(t.findIntersections(new Ray(new Point(-0.5,1,1), new Vector(0,-1,0))),
                "no intersection point");

        //TC15:in vertex
        assertNull(t.findIntersections(new Ray(new Point(-1,1,0), new Vector(0,-1,0))),
                "no intersection point");

        //TC16: on edge's continuation
        assertNull(t.findIntersections(new Ray(new Point(-2,1,-2), new Vector(0,-1,0))),
                "no intersection point");
    }
}