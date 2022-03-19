package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Cylinder class
 */
class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {


        Ray r = new Ray(new Point(0, 0, -2), new Vector(0, 0, 1));
        Cylinder cyl = new Cylinder(r, 5, 8);

        // ============ Equivalence Partitions Tests ==============

        //TC01 - test if point on the side
        assertEquals(cyl.getNormal(new Point(0, 0, 5)), new Vector(0, 0, 1));

        //TC02 - test if point on the starting base
        assertEquals(cyl.getNormal(new Point(3, 4, -2)), new Vector(0, 0, -1));

        //TC03 - test if point on the ending base
        assertEquals(cyl.getNormal(new Point(3, 4, 6)), new Vector(0, 0, 1));


        // =============== Boundary Values Tests ==================

        //TC11 - test if point on center of starting base
        assertEquals(cyl.getNormal(new Point(0, 0, -2)), new Vector(0, 0, -1));

        //TC12 - test if point on center of ending base
        assertEquals(cyl.getNormal(new Point(0, 0, 6)), new Vector(0, 0, 1));

    }
}