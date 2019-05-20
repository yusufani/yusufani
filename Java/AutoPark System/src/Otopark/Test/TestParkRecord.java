package Otopark.Test;

import org.junit.*;
import sample.backend.Date;
import sample.backend.ParkRecord;
import sample.backend.RegularVehicle;
import sample.backend.Time;

import static org.junit.Assert.assertEquals;

public class TestParkRecord {
    ParkRecord parkRecord;
    int expectedValue;
    int realValue;
    @Before
    public void setUp(){
        parkRecord= new ParkRecord(new Time(15,30),new Date(5,12,2019),new RegularVehicle("24EO45"));
        parkRecord.setExitDate(new Date(6,12,2019));
        parkRecord.setExitTime(new Time(16,35));
        expectedValue=25;
    }

    @Test
    public void testGetParkingDuration(){
        realValue=parkRecord.getParkingDuration();
        assertEquals(expectedValue,realValue);
    }

    @After
    public void tearDown(){
        parkRecord = null;
    }

}
