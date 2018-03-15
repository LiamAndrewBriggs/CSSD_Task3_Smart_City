package SmartCity;

import java.util.*;

/**
 * Clock class
 * This class handles the 
 * @author Dran
 */
public class Clock {
    private static Clock clock = null;
    
    public static Clock getInstance()
    {
        if (clock == null)
            clock = new Clock();
        
        return clock;
    }

    /**
     * 
     */
    public double notifyFrequency = 3000;
    /**
     * A list of Sensor Monitors that are registered observers.
     */
    public ArrayList<SensorMonitor> observers = new ArrayList<>();
    /**
     * Returns the first instance of clock, making this class a singleton.
     * @return
     */
    
    private Clock(){
        trackTime();
    }

    
    /**
     * Returns the clock object.
     * @return
     */
    public Clock getClock()
    {
        return clock;
    }
    
    /**
     * Registers a Sensor Monitor as an observer by adding it to the observers list.
     * @param sensorMonitor
     */
    public void registerObserver(SensorMonitor sensorMonitor)
    {
        observers.add(sensorMonitor);
    }
    
    /**
     * Unregisters a Sensor Monitor as an observer by removing it from the observers list.
     * @param sensorMonitor
     */
    public void unregisterObserver(SensorMonitor sensorMonitor)
    {
        observers.remove(sensorMonitor);
    }
    /**
     * Waits for time to pass before operating.
     */
    public void waitForTime()
    {
        while (notifyFrequency != 0) {
            notifyFrequency--;
        }
    }
    
    private void notifyObservers()
    {
        for (SensorMonitor sensorMonitor : observers)
        {
            sensorMonitor.doTick();
        }
    }
    
    private void trackTime()
    {
        while(observers.isEmpty() == false)
        {
            waitForTime();
            notifyObservers();
        }
    } 
}
