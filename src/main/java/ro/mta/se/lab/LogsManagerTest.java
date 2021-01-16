package ro.mta.se.lab;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LogsManagerTest {
    @Test
    public void testShit(){
        LogsManager logger = mock(LogsManager.class);
        when(logger.logThis(anyString())).thenReturn(0);
        assertEquals(logger.logThis(anyString()),0);
        verify(logger).logThis(anyString());
    }
}
