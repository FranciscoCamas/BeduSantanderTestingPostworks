package org.bedu.testing.ejemplo01.sort;

import java.util.*;

public class Sorter {
    public static List<Integer> Sort(int[] unSortedList) {
        List<Integer> sorted = new ArrayList<Integer>();


        if(unSortedList.length<2)
            for(int i: unSortedList) sorted.add(i);
        else  {
            List<Integer> lower   = new ArrayList<Integer>();
            int medium = unSortedList[0];
            List<Integer> mediums = new ArrayList<Integer>();
            List<Integer> higher  = new ArrayList<Integer>();

            for (int element : unSortedList) {
                if (element > medium) {
                    higher.add(element);
                }
                if (element < medium) {
                    lower.add(element);
                }
                if (element == medium) {
                    mediums.add(element);
                }
            }

            int[] lowers = new int[lower.size()];
            //lower.toArray(lowers);
            for( int i=0;  i< lowers.length; lowers[i]=lower.get(i).intValue() , i++ );


            sorted.addAll(Sort(lowers)  );

            //sorted.add(medium);
            sorted.addAll(mediums);

            int[] highers = new int[higher.size()];
           // higher.toArray(highers);
            for( int i=0;  i< highers.length; highers[i]=higher.get(i).intValue() , i++ );
            sorted.addAll(Sort(highers));

        }



        return sorted;
    }

}
