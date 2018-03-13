package SmartCity;

import java.util.*;

public class Clock {
    public ArrayList<SensorMonitor> observers = new ArrayList<>();
    public double notifyFrequency;
    private static Clock clock;
    
    private Clock()
    {
        this.observers = observers;
        this.notifyFrequency = notifyFrequency;
        this.clock = clock;
    }
    
    public Clock getClock()
    {
        return clock;
    }
    
    public void registerObserver(SensorMonitor sensorMonitor)
    {
        SensorMonitor observer = new SensorMonitor();
        observers.add(observer);
    }
    
    public void unregisterObserver(SensorMonitor sensorMonitor)
    {
        SensorMonitor observer = new SensorMonitor();
        observers.remove(observer);
    }
    
    private void notifyObservers()
    {
        //sensorMonitor.doTick();
    }
    
    private void trackTime()
    {
        while(observers.isEmpty() == false)
        {
            waitForTime();
            notifyObservers();
        }
    }
    
    public static Clock getInstance()
    {
        if(clock == null)
        {
            clock = new Clock();
        }
        return clock;
    }
    
    public void waitForTime()
    {
        
    }
}
