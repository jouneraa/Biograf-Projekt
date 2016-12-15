

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ShowTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ShowTest
{
    private Show show1;

    /**
     * Default constructor for test class ShowTest
     */
    public ShowTest()
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
        show1 = new Show(111, 111, 111, "19:30");
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
    public void testgetShowId()
    {
        assertEquals(111, show1.getShowId());
    }

    @Test
    public void testGetMovieId()
    {
        assertEquals(111, show1.getMovieId());
    }

    @Test
    public void testGetAuditoriumId()
    {
        assertEquals(111, show1.getAuditoriumId());
    }

    @Test
    public void testGetStartTime()
    {
        assertEquals("19:30", show1.getStartTime());
    }
}




