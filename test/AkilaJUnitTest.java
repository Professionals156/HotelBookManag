/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hotel.credit.CreditCard;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Room;

import static org.junit.Assert.*;

import java.util.Date;

/**
 *
 * @author sandun
 */
public class AkilaJUnitTest {
    
    public AkilaJUnitTest() {
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
    public void hello() throws Exception {

        Guest guest=null;
        Date bookedArrival = null;
        int stayLength=0;
        int numberOfOccupants=0;
        CreditCard creditCard = null;

        Room myRoom = new Room(numberOfOccupants, null);

        Booking booking = new Booking(guest, myRoom, bookedArrival, stayLength, numberOfOccupants, creditCard);
        myRoom.checkout(booking);
        boolean expectedResult = true;
        boolean isReady = myRoom.isReady();
        assertEquals(expectedResult,isReady);
    
    }
}
