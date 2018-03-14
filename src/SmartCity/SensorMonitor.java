package SmartCity;

/**
 *
 * @author User
 */
import java.util.*;

public class SensorMonitor {
    
    private Double interval;
    private Boolean isActive;
    private Long lastReadingTime;
    private SensorStation observer;
    public Data reading;
    public Integer readingsCount;
    private Sensor sensor;
    private String sensorMonitorID;
    
    
    public SensorMonitor()
    {
        sensorMonitorID = UUID.randomUUID().toString();
        sensor = new FloodSensor();
        isActive = true;
        interval = 70.00;
        reading = new Data();
    }
    
    public SensorMonitor(Object newIsActive, double newIsInterval, Object newDesc)
    {
        sensorMonitorID = UUID.randomUUID().toString();
        interval = newIsInterval;
        
        if (newIsActive.equals("Active")) {
            isActive = true;
        } else {
            isActive = false;
        }
        
        if (newDesc.equals("Bin Sensor")) {
            sensor = new BinSensor();
        } else if (newDesc.equals("Flood Sensor")) {
            sensor = new FloodSensor();
        } else {
            sensor = new TrafficSensor();
        }
        
        reading = new Data();
    }
    
    public void setFrequency(String frequency) {
        interval = Double.parseDouble(frequency);
    }
    
    public String getID() {
        return sensorMonitorID;
    }
    
    public Double getInterval() {
        return interval;
    }
    
    public Sensor getSensor() {
        return sensor;
    }
    
    public Boolean getStatus() {
        return isActive;
    }
    public void pollData()
    {
        
    }
    
    public void registerObserver(SensorStation newObserver) {
        
    }
}
