package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}