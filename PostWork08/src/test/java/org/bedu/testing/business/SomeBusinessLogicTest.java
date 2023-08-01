package org.bedu.testing.business;

import org.bedu.testing.services.ISomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SomeBusinessLogicTest {
    @InjectMocks
    SomeBusinessLogic business;

    @Mock
    private ISomeDataService dataServiceMock;

    @Test
    public void severalValuesAdd() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3},new int[]{},new int[]{5});
        assertEquals(6, business.calculateSumWithADataService());
        assertEquals(0, business.calculateSumWithADataService());
        assertEquals(5, business.calculateSumWithADataService());
    }

    @Test
    public void severalValuesSub() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3},new int[]{},new int[]{5});
        assertEquals(-6, business.calculateSubstractionWithADataService());
        assertEquals( 0,  business.calculateSubstractionWithADataService());
        assertEquals(-5, business.calculateSubstractionWithADataService());
    }

    @Test
    public void calculateSumUsingDataService_basic() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        assertEquals(6, business.calculateSumWithADataService());
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, business.calculateSumWithADataService());
    }

    @Test
    public void calculateSumUsingDataService_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(5, business.calculateSumWithADataService());
    }

    @Test
    public void calculateSubstractionUsingDataService_basic() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        assertEquals(-6, business.calculateSubstractionWithADataService());
    }

    @Test
    public void calculateSubstractionUsingDataService_empty() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, business.calculateSubstractionWithADataService());
    }

    @Test
    public void calculateSubstracionUsingDataService_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(-5, business.calculateSubstractionWithADataService());
    }
}