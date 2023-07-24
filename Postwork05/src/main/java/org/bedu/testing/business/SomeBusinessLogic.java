package org.bedu.testing.business;

import org.bedu.testing.services.SomeDataService;

import java.util.Arrays;

public class SomeBusinessLogic {
    private SomeDataService someDataService;
    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }
    public int calculateSum(int[] data) {
        // 'conventional code'
        /*
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;
        */
        //Functional Style
        return Arrays.stream(data).reduce(Integer::sum).orElse(0);
    }

    public int calculateSumWithADataService() {
        int sum = 0;
        int[] data = someDataService.retrieveAllData();
        for(int value:data) {
            sum += value;
        }

        return sum;
    }
    public int  calculateSubstractionWithADataService() {
        int sum = 0;
        int[] data = someDataService.retrieveAllData();
        for(int value:data) {
            sum -= value;
        }

        return sum;
    }
}
