/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hotel.entities.Booking;
import hotel.entities.ServiceCharge;
import hotel.entities.ServiceType;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sandun
 */
public class SandunJUnitTest {
    
    public SandunJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void testAddServiceCharge() {
        
    }
    
    @Test
    public void testCheckIn() {
        Booking booking = new Booking();
        booking.checkIn();
        boolean isCheckedIn; 
        boolean expectedResult = true;
        isCheckedIn = booking.isCheckedIn();
        assertEquals(expectedResult,isCheckedIn);
    }
    
    @Test
    public void testCheckOut() {
        Booking booking = new Booking();
        booking.checkOut();
        boolean isCheckout; 
        boolean expectedResult = true;
        isCheckout = booking.isCheckedOut();
        assertEquals(expectedResult,isCheckout);
    }
    
}
