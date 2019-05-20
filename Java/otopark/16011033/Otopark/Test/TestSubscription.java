package Otopark.Test;

import org.junit.*;
import Otopark.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestSubscription {
    Subscription subscription1;
    Subscription subscription2;
    @Before
    public void setUp(){
        Date.setToday(new Date(8,4,2019));
    subscription1 = new Subscription(new Date(5,3,2019),new Date(5,4,2019)); //must be invalid value
    subscription2 = new Subscription(new Date(5,3,2019),new Date(5,6,2019)); //must be valid value
    }

    @Test
    public void testIsValid(){
        assertFalse(subscription1.isValid());
    }
    @Test
    public void testIsValid2(){
        assertTrue(subscription2.isValid());
    }

    @After
    public void tearDown(){
        subscription1 =null;
        subscription2 =null;
    }

}
