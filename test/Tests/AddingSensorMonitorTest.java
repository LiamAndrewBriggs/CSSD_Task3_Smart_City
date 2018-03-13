/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import SmartCity.*; 

/**
 *
 * @author Dran
 */
public class AddingSensorMonitorTest {
    
    @Test
    public void testAddingSensorMonitor() {
        
    SensorMonitor testSensor = new SensorMonitor();    
    assertNotNull(testSensor.getInterval());
    }
}
