

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MovieTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MovieTest
{
    private Movie movie1;

    /**
     * Default constructor for test class MovieTest
     */
    public MovieTest()
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
        movie1 = new Movie(111, "aa");
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
    public void testGetTitle()
    {
        assertEquals("aa", movie1.getTitle());
    }

    @Test
    public void testGetMovieId()
    {
    }
}


