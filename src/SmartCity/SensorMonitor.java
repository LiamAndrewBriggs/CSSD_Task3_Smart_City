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
    
    
    
}
