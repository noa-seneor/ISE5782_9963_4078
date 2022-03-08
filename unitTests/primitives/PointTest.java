package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(4,5,6);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that substract result is proper
        assertEquals(new Vector(3,3,3),p2.subtract(p1),"Bad substract result");

        // ============ Boundary values Tests ==============

        //TC11: test for creating (0,0,0) vector
        assertThrows(IllegalArgumentException.class,
                ()-> p1.subtract(p1),
                "Constructed (0,0,0) vector");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1,2,3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that add result is proper
        assertEquals(new Point(4,4,4),p1.add(new Vector(3,2,1)),"Bad add result");

    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(7,8,9);

        // ============ Equivalence Partitions Tests ==============

        // TC01: test that distancesquared result is proper
        assertEquals(90,p1.distanceSquared(new Point(2,4,2)),"Bad distanceSquared result");


    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}
     */
    @Test
    void testDistance() {
        Point p1 = new Point(7,8,9);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that distance result is proper
        assertEquals(Math.sqrt(90),p1.distance(new Point(2,4,2)),0.00001,"Bad distance result");
    }
}