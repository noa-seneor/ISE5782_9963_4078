package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 */
class SphereTest {

    /**
     * Test method for {@link Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============

        Point center = new Point(0,0,0);
        Point p = new Point(8,0,0);
        Sphere s = new Sphere(center,8);

        // TC01: here's a simple test
        assertEquals(new Vector(1,0,0),s.getNormal(p),"bad normal for sphere");

    }
}