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
public class SensorStationTest {
    
    public SensorStationTest() {
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
     * Test of addSensorMonitor method, of class SensorStation.
     */
    @Test
    public void testAddSensorMonitor() {
        System.out.println("addSensorMonitor");
        SensorMonitor newStation = new SensorMonitor();
        SensorStation instance = new SensorStation();
        instance.addSensorMonitor(newStation);
        assertTrue(instance.getSensorMonitors().contains(newStation));
    }

    /**
     * Test of getCoords method, of class SensorStation.
     */
    @Test
    public void testGetCoords() {
        System.out.println("getCoords");
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        ArrayList<Double> expResult = null;
        ArrayList<Double> result = instance.getCoords();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setCoords method, of class SensorStation.
     */
    @Test
    public void testSetCoords() {
        System.out.println("setCoords");
        ArrayList<Double> coords = null;
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        instance.setCoords(coords);
        assertNull(instance.getCoords());
        
    }

    /**
     * Test of getSensorMonitor method, of class SensorStation.
     */
    @Test
    public void testGetSensorMonitor() {
        System.out.println("getSensorMonitor");
        String monitorID = "1";
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        SensorMonitor result = instance.getSensorMonitor(monitorID);
        assertNotNull(result);
    }

    /**
     * Test of getSensorMonitors method, of class SensorStation.
     */
    @Test
    public void testGetSensorMonitors() {
        System.out.println("getSensorMonitors");
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        ArrayList<SensorMonitor> result = instance.getSensorMonitors();
        assertNotNull(result);
    }

    /**
     * Test of setSensorMonitors method, of class SensorStation.
     */
    @Test
    public void testSetSensorMonitors() {
        System.out.println("setSensorMonitors");
        ArrayList<SensorMonitor> sensorMonitors = null;
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        instance.setSensorMonitors(sensorMonitors);
        assertNull(instance.getSensorMonitors());
    }

    /**
     * Test of getStationID method, of class SensorStation.
     */
    @Test
    public void testGetStationID() {
        System.out.println("getStationID");
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        String expResult = "1";
        String result = instance.getStationID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStationID method, of class SensorStation.
     */
    @Test
    public void testSetStationID() {
        System.out.println("setStationID");
        String stationID = "2";
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        instance.setStationID(stationID);
        assertEquals("2", instance.getStationID());
        
    }

    /**
     * Test of getStationName method, of class SensorStation.
     */
    @Test
    public void testGetStationName() {
        System.out.println("getStationName");
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        String expResult = "test";
        String result = instance.getStationName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStationName method, of class SensorStation.
     */
    @Test
    public void testSetStationName() {
        System.out.println("setStationName");
        String stationName = "changed";
        SensorStation instance = new SensorStation(1, "test", 1.304, -13.233);
        instance.setStationName(stationName);
        assertEquals("changed", instance.getStationName());
    }
    
}
