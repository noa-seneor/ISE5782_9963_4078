package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.*;

/**
 * Unit tests for primitives.Vector class
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1,2,3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that add result is proper
        Vector v2 = new Vector(3,2,1);
        assertEquals(new Vector(4,4,4),v1.add(v2),"Bad add result");

        // ============ Boundary values Tests ==============

        // TC11: test if exception thrown for (0,0,0) vector
        assertThrows(IllegalArgumentException.class,
                () -> v1.add(new Vector(-1,-2,-3)),
                "does not throw an exception for (0,0,0) Vector");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(5,8,11);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that lengthsquared result is proper
        assertEquals(210,v1.lengthSquared(),"Bad length squared result");
    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    void testLength() {
        Vector v1 = new Vector(5,8,11);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that length result is proper
        assertEquals(Math.sqrt(210),v1.length(),0.00001,"Bad length squared result");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test the result for obtuse angle between vectors
        Vector v1 = new Vector(2,3,1);
        assertEquals(10,v1.dotProduct(new Vector(6,-1,1)),
                "bad dot product for obtuse angle vectors");

        // TC02: Test the result for acute angle between vectors
        assertEquals(19,v1.dotProduct(new Vector(4,3,2)),
                "bad dot product for acute angle vectors");

        // =============== Boundary Values Tests ==================

        Vector v2 = new Vector(5,8,11);

        // TC11: Test the result for parallel vectors
        assertEquals(420,v2.dotProduct(new Vector(10,16,22)),
                "bad dot product for same direction vectors");

        // TC12: Test the result for opposite direction vector
        assertEquals(-420,v2.dotProduct(new Vector(-10,-16,-22)),
                "bad dot product for inverse direction vectors");

        // TC13: Test the result for orthogonal vector
        assertTrue(isZero(v2.dotProduct(new Vector(-11,0,5))),
                "bad dot product for orthogonal vectors");


    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(4,8,5);
        // TC01: test for non normalized vector
        double size = alignZero(v1.length());
        assertEquals(new Vector(4/size,8/size,5/size),v1.normalize(),
                "wrong normalize result");

        // =============== Boundary Values Tests ==================
        // TC11: test of already normalized vector
        Vector v2 = new Vector(1, 0, 0);
        assertEquals(new Vector(1,0,0),v2.normalize(),
                "normalize() modify the already normalize vector");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(11,12,15);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test that scale result is proper
        assertEquals(new Vector(33,36,45),v1.scale(3),"Bad scale result");

        // =============== Boundary Values Tests ==================

        // TC11: test that exception thrown for creating (0,0,0) vector
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "does not throw exception for (0,0,0) Vector");
    }
}