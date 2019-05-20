package Otopark.Test;

import org.junit.*;
import sample.backend.Date;

import static org.junit.Assert.assertEquals;

public class TestDate{
        Date date1;
        Date date2;
        int realVale;
        int expectedValue;
        @Before
        public void setUp(){
                date1=new Date(23,05,2019);
                date2=new Date(25,05,2019);
                expectedValue=2;
        }

        @Test(timeout = 1000)
        public void testGetDifference(){
                realVale=date2.getDifference(date1);
                assertEquals(expectedValue,realVale);
        }

        @After
        public void tearDown(){
                date1=null;
                date2=null;
        }

}
