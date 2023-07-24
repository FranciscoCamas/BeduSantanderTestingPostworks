package org.bedu.testing.services.imp;

import org.bedu.testing.services.SomeDataService;

public class SomeDataServiceStub implements SomeDataService {
    @Override
    public int[] retrieveAllData() {
        return new int[] { 1, 2, 3 };
    }
}
