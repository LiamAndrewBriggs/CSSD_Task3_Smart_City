package SmartCity;

/**
 *
 * @author User
 */
import java.util.*;

public class SensorMonitor {
    
    private String sensorMonitorID;
    private ArrayList<Double> coords = new ArrayList<>();
    private Boolean isActive;
    private Double interval;
    private SensorStation observer;
    private Long lastReadingTime;
    private Sensor sensor;
    public Integer readingsCount;
    public Data reading;
    
    
    public SensorMonitor()
    {
        sensorMonitorID = UUID.randomUUID().toString();
        sensor = new FloodSensor();
        isActive = true;
        interval = 70.00;
        reading = new Data();
    }
    
    public void pollData()
    {
        
    }
    
    public void setLocation(Location location)
    {
        
    }
    
    public Location getLocation()
    {
        Location temp = new Location();
        
        return temp;
    }
    
    public String getID() {
        return sensorMonitorID;
    }
    
    public Sensor getSensor() {
        return sensor;
    }
    
    public Double getInterval() {
        return interval;
    }
    
    public Boolean getStatus() {
        return isActive;
    }
    
    
}
