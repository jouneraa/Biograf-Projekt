

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ReservationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ReservationTest
{
    private Reservation reservat1;

    /**
     * Default constructor for test class ReservationTest
     */
    public ReservationTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        reservat1 = new Reservation(111, 111, 111, 111, 111);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetReservationId()
    {
        assertEquals(111, reservat1.getReservationId());
    }

    @Test
    public void testGetTelephoneNumber()
    {
        assertEquals(111, reservat1.getTelephoneNumber());
    }

    @Test
    public void testGetShowId()
    {
        assertEquals(111, reservat1.getShowId());
    }

    @Test
    public void testGetRowNumber()
    {
        assertEquals(111, reservat1.getRowNumber());
    }

    @Test
    public void testGetColumnNumber()
    {
        assertEquals(111, reservat1.getColumnNumber());
    }
}





