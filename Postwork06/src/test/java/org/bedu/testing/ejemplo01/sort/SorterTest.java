package org.bedu.testing.ejemplo01.sort;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class SorterTest {
    @Test
    public void SortEmptyList_ShouldReturnEmptyList() {
        assertEquals (new ArrayList<Integer>(),
                Sorter.Sort(new int[]{}) );
    }
    @Test
    public void SortListWithOneElement_ShouldReturnSortedList() {
        assertEquals( new ArrayList<Integer>( Arrays.asList(1)),
                     Sorter.Sort(new int[]{1}));
    }
    @Test
    public void SortListWithTwoElementsInCorrectOrder_ShouldReturnSameList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2)),
                    Sorter.Sort(new int[]{1,2}));
    }
    @Test
    public void SortListWithTwoElementsInReverseOrder_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1, 2)),
                Sorter.Sort(new int[]{2, 1}));
    }

    @Test
    public void SortListWithSameTwoElements_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(2, 2)),
                     Sorter.Sort(new int[]{2, 2}));
    }

    @Test
    public void SortListWithThreeElementsFirstTwoSwapped_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2, 3)),
                Sorter.Sort(new int[]{2, 3,1}));
    }

    /* Will not pass*/
    @Test
    public void SortListWithThreeElementsAllSwapped_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2, 3)),
                Sorter.Sort(new int[]{3, 2,1}));
    }

    @Test
    public void SortListWithFourElementsFirstTwoSwapped_ShouldReturnSortedList() {

        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,2, 3)),
                Sorter.Sort(new int[]{3, 2,2,1}));
    }
}