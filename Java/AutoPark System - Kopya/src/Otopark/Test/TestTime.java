package Otopark.Test;

import org.junit.*;
import Otopark.*;

import static org.junit.Assert.assertEquals;

public class TestTime {
    private Time firstTestTime;
    private Time firstTestTime2;
    private int firstExpectedValue;
    private int firstRealValue;
    private Time secondTestTime;
    private Time secondTestTime2;
    private int secondExpectedValue;
    private int secondRealValue;
    @Before
    public void setUp(){
        Date.setToday(new Date(1,1,1));// Added for keep from Null Pointer Exception
        firstTestTime = new Time(21,46);
        firstTestTime2 = new Time(22,12);
        firstExpectedValue =26;
        secondTestTime = new Time(11,15);
        secondTestTime2 = new Time(11,15);
        secondExpectedValue = 0;
    }

    @Test
    public void testGetDifference(){
        firstRealValue = firstTestTime2.getDifference(firstTestTime);
        assertEquals(firstExpectedValue, firstRealValue);
    }

    @Test
    public void testGetDifference2(){
        secondRealValue = secondTestTime2.getDifference(secondTestTime);
        assertEquals(secondExpectedValue, secondRealValue);
    }

    @After
    public void tearDown(){
        firstTestTime =null;
        firstTestTime2 =null;
        secondTestTime =null;
        secondTestTime2 =null;


    }

}
