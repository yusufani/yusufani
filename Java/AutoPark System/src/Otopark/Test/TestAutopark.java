package Otopark.Test;

import org.junit.*;
import sample.backend.*;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class TestAutopark {
    SubscribedVehicle subscribedVehicle1=new SubscribedVehicle("24RK12");
    SubscribedVehicle subscribedVehicle2=new SubscribedVehicle("24UEA12");
    Subscription subscription1=new Subscription(new Date(10,5,2019),new Date(10,10,2019));
    Subscription subscription2=new Subscription(new Date(10,5,2019),new Date(8,8,2019));
    Boolean realValue;
    SubscribedVehicle realValueObject;
    RegularVehicle regularVehicle=new RegularVehicle("123AA12");


    @Before
    public void setUp(){
        subscribedVehicle1.setSubscription(subscription1);
        subscribedVehicle2.setSubscription(subscription2);
        AutoPark.getInstance().addVehicle(subscribedVehicle1);
        AutoPark.getInstance().vehicleEnters("123AA12",Time.getCurrentTime(),Date.getToday(),false);
    }

    @Test
    public void testSearchVehicle(){
        realValueObject=AutoPark.getInstance().searchVehicle("24RK12");
        assertNotNull(realValueObject);
    }
    @Test
    public void testSearchVehicle2(){
        realValueObject=AutoPark.getInstance().searchVehicle("24UU12");
        assertNull(realValueObject);
    }
    @Test
    public void testIsParked(){
        realValue=AutoPark.getInstance().isParked("123AA12");
        assertTrue(realValue);
    }
    @Test
    public void testIsParked2(){
        realValue=AutoPark.getInstance().isParked("123RG12");
        assertFalse(realValue);
    }
    @Test
    public void testAddVehicle(){
        realValue=AutoPark.getInstance().addVehicle(subscribedVehicle2);
        assertTrue(realValue);
    }

    @Test
    public void testAddVehicle2(){
        realValue=AutoPark.getInstance().addVehicle(subscribedVehicle1);
        assertFalse(realValue);
    }
    @Test
    public void testVehicleEnters(){
        realValue=AutoPark.getInstance().vehicleEnters("123RK32",Time.getCurrentTime(),Date.getToday(),false);
        assertTrue(realValue);
    }

    @Test
    public void testVehicleEnters2(){
        realValue=AutoPark.getInstance().vehicleEnters("123AA12",Time.getCurrentTime(),Date.getToday(),false);
        assertFalse(realValue);
    }
    @Test
    public void testVehicleExits(){
        realValue=AutoPark.getInstance().vehicleExits("123AA12",Time.getCurrentTime(),Date.getToday());
        assertTrue(realValue);
    }

    @Test
    public void testVehicleExits2(){
        realValue=AutoPark.getInstance().vehicleExits("456TS123",Time.getCurrentTime(),Date.getToday());
        assertFalse(realValue);
    }

}
