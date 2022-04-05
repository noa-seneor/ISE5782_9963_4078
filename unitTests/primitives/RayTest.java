package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Ray class
 */
class RayTest {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point(0, 0, 10), new Vector(1, 10, -100));

        List<Point> list = new LinkedList<Point>();
        // ============ Boundary values Tests ==============

        //TC01: empty list
        assertNull(ray.findClosestPoint(list), "the list is empty");

        list.add(new Point(0, 2, -10));
        list.add(new Point(1, 1, -100));
        list.add(new Point(-1, 1, -99));
        list.add(new Point(0.5, 0, -100));

        //TC02: first is the closer
        assertEquals(list.get(0), ray.findClosestPoint(list),
                "closest point is first");

        //TC03: last is the closer
        list.add(new Point(0.5, 0, 12));
        assertEquals(list.get(4), ray.findClosestPoint(list),
                "closest point is last");

        // ============ Equivalence Partitions Tests ==============

        // TC11: the middle of the list is the closet
        list.clear();
        list.add(new Point(1, 1, -100));
        list.add(new Point(-1, 1, -99));
        list.add(new Point(0, 2, 15));
        list.add(new Point(0.5, 0, -100));
        list.add(new Point(0.5, 3, 70));
        assertEquals(list.get(2), ray.findClosestPoint(list),
                "closest point is middle");





    }
}