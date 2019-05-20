package Otopark.Test;

import org.junit.*;
import sample.backend.Time;

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
        firstTestTime = new Time(15,23);
        firstTestTime2 = new Time(20,24);
        firstExpectedValue =5;
        secondTestTime = new Time(22,59);
        secondTestTime2 = new Time(1,23);
        secondExpectedValue = 0;
    }

    @Test
    public void testGetDifference(){
        firstRealValue = firstTestTime2.getDifferance(firstTestTime);
        assertEquals(firstExpectedValue, firstRealValue);
    }

    @Test
    public void testGetDifference2(){
        secondRealValue = secondTestTime2.getDifferance(secondTestTime);
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
