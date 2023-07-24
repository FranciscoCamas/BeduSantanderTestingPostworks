package org.bedu.testing.business;

import org.bedu.testing.services.ISomeDataService;

import java.util.Arrays;

public class SomeBusinessLogic {
    private ISomeDataService ISomeDataService;
    public void setSomeDataService(ISomeDataService ISomeDataService) {
        this.ISomeDataService = ISomeDataService;
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
        int[] data = ISomeDataService.retrieveAllData();
        for(int value:data) {
            sum += value;
        }

        return sum;
    }
    public int  calculateSubstractionWithADataService() {
        int sum = 0;
        int[] data = ISomeDataService.retrieveAllData();
        for(int value:data) {
            sum -= value;
        }

        return sum;
    }
}
