package Otopark.Test;

import org.junit.*;
import Otopark.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestParkRecord {
    ParkRecord parkRecord;
    int expectedValue;
    int realValue;
    @Before
    public void setUp(){
        parkRecord= new ParkRecord(new Time(12,20,new Date(11,11,2019)),new RegularVehicle("24EO45"));
        parkRecord.setExitTime(new Time(16,35,new Date (12,11,2019)));
        expectedValue=1695;
    }

    @Test
    public void testGetParkingDuration(){
        realValue=parkRecord.getParkingDuration();
        assertEquals(expectedValue,realValue);
    }
    public void testGetParkingDuration2(){
        parkRecord.setExitTime(new Time (15,35,new Date ( 12,11,2019)));
        realValue=parkRecord.getParkingDuration();
        assertNotEquals(expectedValue,realValue);
    }

    @After
    public void tearDown(){
        parkRecord = null;
    }

}
