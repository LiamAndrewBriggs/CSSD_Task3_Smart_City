package SmartCity;

import java.util.List;
import java.util.UUID;

public class SensorStation {
    
    private String stationID;
    private String stationName;
    private List<SensorMonitor> sensorMonitors;
    private Mothership observer;
    
    public SensorStation(String name){
        stationID = UUID.randomUUID().toString();
        stationName = name;
    }
    
    public void addNewSensorMonitor(){
        
    }
    
    public void removeSensorMonitor(){
        
    }
    
    public PublicInterface receiveSensorData(EmbellishedData temp){
        PublicInterface temp2 = new PublicInterface();
        
        return temp2;
    }
    
    public void updateSensorFrequency(){
    
    }
    
    public void registerObserver(Mothership temp){
        
    }

    public void unregisterObserver(Mothership temp){
        
    }
    
    public List<SensorMonitor> getSensorMonitors(){
        return sensorMonitors;
   }
    
    public SensorMonitor getSensorMonitor(){
        SensorMonitor temp = new SensorMonitor();
        
        return temp;
    }
    
    public String getName() {
        return stationName;
    }
}

