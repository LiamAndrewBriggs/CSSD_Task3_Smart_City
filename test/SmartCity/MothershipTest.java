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
public class MothershipTest {
    
    public MothershipTest() {
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
     * Test of addNewSensorStation method, of class Mothership.
     */
    @Test
    public void testAddNewSensorStation() {
        System.out.println("addNewSensorStation");
        Mothership instance = new Mothership();
        SensorStation sensorStation = new SensorStation();
        instance.addNewSensorStation(sensorStation);
        assertNotNull(instance.getSensorStations());
    }

    /**
     * Test of getSensorStation method, of class Mothership.
     */
    @Test
    public void testGetSensorStation() {
        System.out.println("getSensorStation");
        String sensorID = "1";
        Mothership instance = new Mothership();
        SensorStation expResult = null;
        SensorStation result = instance.getSensorStation(sensorID);
        assertNotNull(result);
    }

    /**
     * Test of getSensorStations method, of class Mothership.
     */
    @Test
    public void testGetSensorStations() {
        System.out.println("getSensorStations");
        Mothership instance = new Mothership();
        ArrayList<SensorStation> expResult = null;
        ArrayList<SensorStation> result = instance.getSensorStations();
        assertNotSame(expResult, result);
    }
}
