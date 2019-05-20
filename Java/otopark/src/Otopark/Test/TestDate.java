package Otopark.Test;

import org.junit.*;
import Otopark.*;
import static org.junit.Assert.assertEquals;

public class TestDate{
        Date date1;
        Date date2;
        int realValue;
        int expectedValue;
        @Before
        public void setUp(){
                date1=new Date(23,05,2019);
                date2=new Date(25,05,2019);
                expectedValue=2880;
        }

        @Test(timeout = 1000)
        public void testGetDifference(){
                realValue=date2.valueOfDate()-date1.valueOfDate();
                assertEquals(expectedValue,realValue);
        }
        public void testGetDifference2(){
                realValue=date2.valueOfDate()-date2.valueOfDate();
                assertEquals(0,realValue);
        }
        @After
        public void tearDown(){
                date1=null;
                date2=null;
        }

}
