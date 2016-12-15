

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DataFactoryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DataFactoryTest
{
    private DataFactory dataFact1;

    /**
     * Default constructor for test class DataFactoryTest
     */
    public DataFactoryTest()
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
        dataFact1 = DataFactory.getInstance();
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
    public void testGetCustomer()
    {
        dataFact1.addCustomer(12345, "aa");
        Customer customer1 = dataFact1.getCustomer(12345);
        assertEquals(12345, customer1.getTelephoneNumber());
    }

    @Test
    public void testAddExistingCustomer()
    {
        dataFact1.addCustomer(12345, "aa");
        assertEquals(false, dataFact1.addCustomer(12345, "aa"));
    }

    
    
     @Test
    public void testAddNewCustomer()
    {
        dataFact1.deleteCustomer(12345);
        assertEquals(true, dataFact1.addCustomer(12345, "aa"));
    }
}



