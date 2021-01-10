package com.adlardco.sudoku.service.option;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OptionSetTest {

    @Test
    public void testGetSize() {
        var set = OptionSet.of(1, 2);
        assertEquals(2, set.getSize());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(OptionSet.of(1).isEmpty());
        assertTrue(OptionSet.of().isEmpty());
    }

    @Test
    public void testRemove() {
        var set = OptionSet.of(1, 2);
        assertEquals(2, set.getSize());
        set.remove(3);
        assertEquals(OptionSet.of(1, 2), set);
        set.remove(2);
        assertEquals(OptionSet.of(1), set);
        set.remove(1);
        assertEquals(OptionSet.of(), set);
    }

    @Test
    public void testClear() {
        var set = OptionSet.of(1, 2);
        set.clear();
        assertEquals(0, set.getSize());
        assertTrue(set.isEmpty());
    }

    @Test
    public void testRemoveSet() {
        var set1 = OptionSet.of(1, 2);
        var set2 = OptionSet.of(2, 3);
        set1.remove(set2);
        assertEquals(OptionSet.of(1), set1);
        assertEquals(OptionSet.of(2, 3), set2);
    }

    @Test
    public void testSet() {
        var set = OptionSet.of(1, 2);
        set.set(2);
        assertEquals(OptionSet.of(2), set);
    }

    @Test
    public void testSetWithOptionSet() {
        var set = OptionSet.of(1, 2);
        set.set(OptionSet.of(3, 4));
        assertEquals(OptionSet.of(3, 4), set);
    }

    @Test
    public void testToInt() {
        assertEquals(0, OptionSet.of().toInt());
        assertEquals(1, OptionSet.of(1).toInt());
        assertEquals(0, OptionSet.of(1, 2).toInt());
    }

    @Test
    public void testGetNextHighest() {
        var set = OptionSet.of(1, 2);
        assertEquals(Optional.of(1), set.getNextHighest(0));
        assertEquals(Optional.of(2), set.getNextHighest(1));
        assertEquals(Optional.empty(), set.getNextHighest(2));
    }

    @Test
    public void testToString() {
        var set = OptionSet.of(1, 2);
        assertEquals("[1, 2]", set.toString());
    }
}