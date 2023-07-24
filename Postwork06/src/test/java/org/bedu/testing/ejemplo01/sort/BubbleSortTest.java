package org.bedu.testing.ejemplo01.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class BubbleSortTest {

    @Test
    public void SortEmptyList_ShouldReturnEmptyList() {
        assertEquals (new ArrayList<Integer>(),
                     BubbleSort.bubbleSort(new int[]{}) );
    }
    @Test
    public void SortListWithOneElement_ShouldReturnSortedList() {
        assertEquals( new ArrayList<Integer>( Arrays.asList(1)),
                     BubbleSort.bubbleSort(new int[]{1}));
    }

    @Test
    public void SortListWithTwoElementsInCorrectOrder_ShouldReturnSameList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2)),
                     BubbleSort.bubbleSort(new int[]{1,2}));
    }

    @Test
    public void SortListWithSameTwoElements_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(2, 2)),
                     BubbleSort.bubbleSort(new int[]{2, 2}));
    }
    @Test
    public void SortListWithTwoElementsInReverseOrder_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1, 2)),
                BubbleSort.bubbleSort(new int[]{2, 1}));
    }

    @Test
    public void SortListWithThreeElements_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2, 3)),
                BubbleSort.bubbleSort(new int[]{2, 3,1}));
    }

    @Test
    public void SortListWithThreeElementsTwoDuplicates_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2, 2)),
                BubbleSort.bubbleSort(new int[]{2, 2,1}));
    }

    @Test
    public void SortListWithFourElementsTwoDuplicates_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2, 2,3)),
                BubbleSort.bubbleSort(new int[]{3,2, 2,1}));
    }

    @Test
    public void SortListWithFiveElementsTwoDuplicates_ShouldReturnSortedList() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2, 3,4,5)),
                BubbleSort.bubbleSort(new int[]{5,4,3,2,1}));
    }
}
