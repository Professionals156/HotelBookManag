
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hotel.entities.Room;

import static org.junit.Assert.*;

/**
 *
 * @author dismika 11634080
 */
public class DismikaJUnitTest {

    public DismikaJUnitTest() {
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
    public void TestCheckIn() throws Exception {

        Room testRoom = new Room(0, null);
        testRoom.checkin();

        boolean expectedResult = true;

        boolean isReady = testRoom.isReady();
        assertEquals(expectedResult, isReady);

    }
}
