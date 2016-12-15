

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AuditoriumTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AuditoriumTest
{
    private Auditorium auditori1;

    /**
     * Default constructor for test class AuditoriumTest
     */
    public AuditoriumTest()
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
        auditori1 = new Auditorium(111, "aa", 11, 11);
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
    public void testGetAuditoriumId()
    {
        assertEquals(111, auditori1.getAuditoriumId());
    }

    @Test
    public void testGetName()
    {
        assertEquals("aa", auditori1.getName());
    }

    @Test
    public void testgetRowNumber()
    {
        assertEquals(11, auditori1.getRowNumber());
    }

    @Test
    public void testgetColumnNumber()
    {
        assertEquals(11, auditori1.getColumnNumber());
    }
}




