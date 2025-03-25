package uvg.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TestLisp {
    @Test
    void testAdd() {
        assertEquals(10, BuiltInFunctions.add(Arrays.asList(1, 2, 3, 4)));
        assertEquals(0, BuiltInFunctions.add(Collections.emptyList()));
    }

    @Test
    void testSubtract() {
        assertEquals(2, BuiltInFunctions.subtract(Arrays.asList(10, 5, 3)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.subtract(Collections.emptyList()));
    }

    @Test
    void testMultiply() {
        assertEquals(24, BuiltInFunctions.multiply(Arrays.asList(1, 2, 3, 4)));
        assertEquals(1, BuiltInFunctions.multiply(Collections.emptyList()));
    }

    @Test
    void testDivide() {
        assertEquals(2, BuiltInFunctions.divide(Arrays.asList(20, 5, 2)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.divide(Collections.emptyList()));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.divide(Arrays.asList(10, 0)));
    }

    @Test
    void testEquals() {
        assertTrue(BuiltInFunctions.equals(Arrays.asList(5, 5, 5)));
        assertFalse(BuiltInFunctions.equals(Arrays.asList(5, 5, 6)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.equals(Collections.emptyList()));
    }

    @Test
    void testLessThan() {
        assertTrue(BuiltInFunctions.lessThan(Arrays.asList(3, 5)));
        assertFalse(BuiltInFunctions.lessThan(Arrays.asList(5, 3)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.lessThan(Arrays.asList(1)));
    }

    @Test
    void testLessThanOrEqual() {
        assertTrue(BuiltInFunctions.lessThanOrEqual(Arrays.asList(3, 5)));
        assertTrue(BuiltInFunctions.lessThanOrEqual(Arrays.asList(5, 5)));
        assertFalse(BuiltInFunctions.lessThanOrEqual(Arrays.asList(6, 5)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.lessThanOrEqual(Arrays.asList(1)));
    }

    @Test
    void testGreaterThan() {
        assertTrue(BuiltInFunctions.greaterThan(Arrays.asList(5, 3)));
        assertFalse(BuiltInFunctions.greaterThan(Arrays.asList(3, 5)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.greaterThan(Arrays.asList(1)));
    }

    @Test
    void testGreaterThanOrEqual() {
        assertTrue(BuiltInFunctions.greaterThanOrEqual(Arrays.asList(5, 3)));
        assertTrue(BuiltInFunctions.greaterThanOrEqual(Arrays.asList(5, 5)));
        assertFalse(BuiltInFunctions.greaterThanOrEqual(Arrays.asList(3, 5)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.greaterThanOrEqual(Arrays.asList(1)));
    }

    @Test
    void testAnd() {
        assertTrue(BuiltInFunctions.and(Arrays.asList(true, true, true)));
        assertFalse(BuiltInFunctions.and(Arrays.asList(true, false, true)));
    }

    @Test
    void testOr() {
        assertTrue(BuiltInFunctions.or(Arrays.asList(false, true, false)));
        assertFalse(BuiltInFunctions.or(Arrays.asList(false, false, false)));
    }

    @Test
    void testAtom() {
        assertTrue(BuiltInFunctions.atom(5));
        assertFalse(BuiltInFunctions.atom(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testList() {
        List<Object> input = Arrays.asList(1, 2, 3);
        assertEquals(input, BuiltInFunctions.list(input));
    }
}
