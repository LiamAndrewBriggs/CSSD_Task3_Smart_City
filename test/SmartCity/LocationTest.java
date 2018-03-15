/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartCity;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dran
 */
public class LocationTest {
    
    public LocationTest() {
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

    /**
     * Test of getCoords method, of class Location.
     */
    @Test
    public void testGetCoords() {
        System.out.println("getCoords");
        Location instance = new Location();
        ArrayList<Double> expResult = null;
        ArrayList<Double> result = instance.getCoords();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Location.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Location instance = new Location();
        instance.setLatitude(21.323);
        double expResult = 0.0;
        double result = instance.getLatitude();
        assertNotEquals(expResult, result, 0.0);

    }

    /**
     * Test of setLatitude method, of class Location.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 12.35;
        Location instance = new Location();
        instance.setLatitude(latitude);
        assertNotNull(latitude);

    }

    /**
     * Test of getLongitude method, of class Location.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Location instance = new Location();
        instance.setLongitude(-12.4551);
        double expResult = 0.0;
        double result = instance.getLongitude();
        assertNotEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setLongitude method, of class Location.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = -12.452;
        Location instance = new Location();
        instance.setLongitude(longitude);
        assertNotNull(longitude);
    }
    
}
