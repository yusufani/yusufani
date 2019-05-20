package Otopark.Test;

import org.junit.*;
import sample.backend.Date;
import sample.backend.Subscription;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestSubscription {
    Subscription subscription;
    Subscription subscription1;
    @Before
    public void setUp(){
    subscription = new Subscription(new Date(5,3,2019),new Date(5,4,2019)); //must be invalid value
    subscription1 = new Subscription(new Date(5,3,2019),new Date(5,6,2019)); //must be valid value
    }

    @Test
    public void testIsValid(){
        assertFalse(subscription.isValid());
    }
    @Test
    public void testIsValid2(){
        assertTrue(subscription1.isValid());
    }

    @After
    public void tearDown(){
        subscription =null;
        subscription1 =null;
    }

}
