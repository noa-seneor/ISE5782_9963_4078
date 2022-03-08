package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {

        Tube t = new Tube(new Ray(new Point(0,0,-3),new Vector(0,0,1)),3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test the result of getNormal for tube is proper

        Point p1 = new Point(0,-3,1);
        assertEquals(new Vector(0,-1,0),t.getNormal(p1), "bad Normal for Tube");

        // =============== Boundary Values Tests ==================

        // TC11: Test the result for point in front of head ray
        Point p2 = new Point(3,0,-3);
        assertEquals(new Vector(1,0,0),t.getNormal(p2), "bad tube normal for point in front of ray");

    }
}