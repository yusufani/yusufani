package Otopark.Test;

import org.junit.*;
import Otopark.*;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class TestAutopark {
    SubscribedVehicle subscribedVehicle1=new SubscribedVehicle("12ab34");
    SubscribedVehicle subscribedVehicle2=new SubscribedVehicle("56ab78");
    Subscription subscription1;
    Subscription subscription2;
    Boolean realValue;
    SubscribedVehicle realObject;
    Time currentTime;
    String errorMessage = "Exit date can not be before than Begin date ";
    @Before
    public void setUp() throws Exception{
        Date.setToday(new Date(8,8,2019));
        RegularVehicle regularVehicle=new RegularVehicle("12ab34");
        currentTime = new Time ( 10,25);
        subscription1=new Subscription(new Date(15,5,2019),new Date(15,10,2019));
        subscription2=new Subscription(new Date(10,5,2019),new Date(8,8,2019));
        subscribedVehicle1.setSubscription(subscription1);
        subscribedVehicle2.setSubscription(subscription2);
        AutoPark.getInstance().addVehicle(subscribedVehicle1);
        AutoPark.getInstance().vehicleEnters("123AA34",currentTime ,false);

    }

    @Test
    public void testSearchVehicle(){
        realObject =AutoPark.getInstance().searchVehicle("12ab34");
        assertNotNull(realObject);
    }
    @Test
    public void testSearchVehicle2(){
        realObject =AutoPark.getInstance().searchVehicle("56ab78");
        assertNull(realObject);
    }
    @Test
    public void testIsParked(){
        realValue=AutoPark.getInstance().isParked("123AA34");
        assertTrue(realValue);
    }
    @Test
    public void testIsParked2(){
        realValue=AutoPark.getInstance().isParked("12DB12");
        assertFalse(realValue);
    }
    @Test
    public void testAddVehicle()   {
        realValue=AutoPark.getInstance().addVehicle(subscribedVehicle2);
        assertTrue(realValue);
    }

    @Test
    public void testAddVehicle2()throws  Exception{
        realValue=AutoPark.getInstance().addVehicle(subscribedVehicle1);
        assertFalse(realValue);
    }
    @Test
    public void testVehicleEnters(){
        realValue=AutoPark.getInstance().vehicleEnters("123RK32",currentTime,false);
        assertTrue(realValue);
    }

    @Test
    public void testVehicleEnters2(){
        realValue=AutoPark.getInstance().vehicleEnters("123AA34",currentTime,false);
        assertFalse(realValue);
    }
    @Test
    public void testVehicleExits(){
        try {
            realValue=AutoPark.getInstance().vehicleExits("123AA34",currentTime);
            assertTrue(realValue);
        }catch (Exception e ){
            e.printStackTrace();
            assertEquals(errorMessage,e.getMessage());
        }

    }

    @Test
    public void testVehicleExits2(){
        try {
            realValue=AutoPark.getInstance().vehicleExits("456TS123",currentTime);
            assertFalse(realValue);
        }catch (Exception e ){
            e.printStackTrace();
            assertEquals(errorMessage,e.getMessage());
        }
    }

}
