/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartCity;

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
public class SensorMonitorTest {
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    public SensorMonitorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInterval method, of class SensorMonitor.
     */
    @Test
    public void testGetInterval() {
        System.out.println("getInterval");
        SensorMonitor instance = new SensorMonitor(1, "Test Description", "Active", 70, null);
        double expResult = 70;
        double result = instance.getInterval();
        assertEquals(expResult, result, 1);
    }


    /**
     * Test of getIsActive method, of class SensorMonitor.
     */
    @Test
    public void testGetIsActive() {
        System.out.println("getIsActive");
        SensorMonitor instance = new SensorMonitor(1, "Test Description", "Active", 70, null);
        Boolean expResult = true;
        Boolean result = instance.getIsActive();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSensor method, of class SensorMonitor.
     */
    @Test
    public void testGetSensor() {
        System.out.println("getSensor");
        SensorMonitor instance = new SensorMonitor(1, "Test Description", "Active", 70, null);
        Sensor result = instance.getSensor();
        assertNotNull(result);  
    }

    /**
     * Test of getSensorMonitorID method, of class SensorMonitor.
     */
    @Test
    public void testGetSensorMonitorID() {
        System.out.println("getSensorMonitorID");
        SensorMonitor instance = new SensorMonitor(1, "Test Description", "Active", 70, null);
        String expResult = "1";
        String result = instance.getSensorMonitorID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInterval method, of class SensorMonitor.
     */
    @Test
    public void testSetInterval() {
        System.out.println("setInterval");
        double interval = 70;
        SensorMonitor instance = new SensorMonitor();
        instance.setInterval(interval);
        assertTrue(instance.getInterval() == 70);
    }
    /**
     * Test of setIsActive method, of class SensorMonitor.
     */
    @Test
    public void testSetIsActive() {
        System.out.println("setIsActive");
        Boolean isActive = true;
        SensorMonitor instance = new SensorMonitor();
        instance.setIsActive(isActive);
        assertTrue(instance.getIsActive() == true);
    }

    /**
     * Test of setSensor method, of class SensorMonitor.
     */
    @Test
    public void testSetSensor() {
        System.out.println("setSensor");
        Sensor sensor = null;
        SensorMonitor instance = new SensorMonitor();
        instance.setSensor(sensor);
        assertNull(instance.getSensor());
    }
    /**
     * Test of setSensorMonitorID method, of class SensorMonitor.
     */
    @Test
    public void testSetSensorMonitorID() {
        System.out.println("setSensorMonitorID");
        String sensorMonitorID = "35";
        SensorMonitor instance = new SensorMonitor();
        instance.setSensorMonitorID(sensorMonitorID);
        assertTrue(instance.getSensorMonitorID().equals("35"));
    }
    
}
