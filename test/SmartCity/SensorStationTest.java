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
        SensorStation instance = new SensorStation();
        ArrayList<Double> expResult = null;
        ArrayList<Double> result = instance.getCoords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoords method, of class SensorStation.
     */
    @Test
    public void testSetCoords() {
        System.out.println("setCoords");
        ArrayList<Double> coords = null;
        SensorStation instance = new SensorStation();
        instance.setCoords(coords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObserver method, of class SensorStation.
     */
    @Test
    public void testGetObserver() {
        System.out.println("getObserver");
        SensorStation instance = new SensorStation();
        Mothership expResult = null;
        Mothership result = instance.getObserver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setObserver method, of class SensorStation.
     */
    @Test
    public void testSetObserver() {
        System.out.println("setObserver");
        Mothership observer = null;
        SensorStation instance = new SensorStation();
        instance.setObserver(observer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSensorMonitor method, of class SensorStation.
     */
    @Test
    public void testGetSensorMonitor() {
        System.out.println("getSensorMonitor");
        String monitorID = "";
        SensorStation instance = new SensorStation();
        SensorMonitor expResult = null;
        SensorMonitor result = instance.getSensorMonitor(monitorID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSensorMonitors method, of class SensorStation.
     */
    @Test
    public void testGetSensorMonitors() {
        System.out.println("getSensorMonitors");
        SensorStation instance = new SensorStation();
        ArrayList<SensorMonitor> expResult = null;
        ArrayList<SensorMonitor> result = instance.getSensorMonitors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSensorMonitors method, of class SensorStation.
     */
    @Test
    public void testSetSensorMonitors() {
        System.out.println("setSensorMonitors");
        ArrayList<SensorMonitor> sensorMonitors = null;
        SensorStation instance = new SensorStation();
        instance.setSensorMonitors(sensorMonitors);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStationID method, of class SensorStation.
     */
    @Test
    public void testGetStationID() {
        System.out.println("getStationID");
        SensorStation instance = new SensorStation();
        String expResult = "";
        String result = instance.getStationID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStationID method, of class SensorStation.
     */
    @Test
    public void testSetStationID() {
        System.out.println("setStationID");
        String stationID = "";
        SensorStation instance = new SensorStation();
        instance.setStationID(stationID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStationName method, of class SensorStation.
     */
    @Test
    public void testGetStationName() {
        System.out.println("getStationName");
        SensorStation instance = new SensorStation();
        String expResult = "";
        String result = instance.getStationName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStationName method, of class SensorStation.
     */
    @Test
    public void testSetStationName() {
        System.out.println("setStationName");
        String stationName = "";
        SensorStation instance = new SensorStation();
        instance.setStationName(stationName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of receiveSensorData method, of class SensorStation.
     */
    @Test
    public void testReceiveSensorData() {
        System.out.println("receiveSensorData");
        EmbellishedData temp = null;
        SensorStation instance = new SensorStation();
        PublicInterface expResult = null;
        PublicInterface result = instance.receiveSensorData(temp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerObserver method, of class SensorStation.
     */
    @Test
    public void testRegisterObserver() {
        System.out.println("registerObserver");
        Mothership temp = null;
        SensorStation instance = new SensorStation();
        instance.registerObserver(temp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSensorMonitor method, of class SensorStation.
     */
    @Test
    public void testRemoveSensorMonitor() {
        System.out.println("removeSensorMonitor");
        SensorStation instance = new SensorStation();
        instance.removeSensorMonitor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterObserver method, of class SensorStation.
     */
    @Test
    public void testUnregisterObserver() {
        System.out.println("unregisterObserver");
        Mothership temp = null;
        SensorStation instance = new SensorStation();
        instance.unregisterObserver(temp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSensorFrequency method, of class SensorStation.
     */
    @Test
    public void testUpdateSensorFrequency() {
        System.out.println("updateSensorFrequency");
        SensorStation instance = new SensorStation();
        instance.updateSensorFrequency();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
