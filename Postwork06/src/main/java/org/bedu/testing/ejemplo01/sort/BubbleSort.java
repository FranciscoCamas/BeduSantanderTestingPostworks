package org.bedu.testing.ejemplo01.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleSort {
    public static List<Integer> bubbleSort(int[] unSortedList) {
        int temp             = 0;
        int n                = unSortedList.length;
        List<Integer> sorted = new ArrayList<Integer>();

        //Preserve the original array
        int[] auxShort = Arrays.copyOf (unSortedList,unSortedList.length); ;

        for(int i=0; i < n; i++)
           for(int j=1; j < (n-i); j++)
              if(auxShort[j-1] > auxShort[j]){
                   //swap elements
                  temp          = auxShort[j-1];
                  auxShort[j-1] = auxShort[j];
                  auxShort[j]   = temp;
                }

        for(int i: auxShort) sorted.add(i);

        return sorted;
    }
}