package ro.mta.se.lab;

import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherAPIManagerTest {
    @Test
    public void APICall() {
        String str = "notAcity";
        assertNull(WeatherAPIManager.getJsonMapFromAPI(str));
    }
}
