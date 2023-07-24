package org.bedu.testing.services.imp;

import org.bedu.testing.services.ISomeDataService;

public class SomeDataServiceStub implements ISomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[] { 1, 2, 3 };
    }
}
