package SmartCity;

import java.util.List;

public class SensorStation {
    
    private String stationID;
    private List<SensorMonitor> sensorMonitors;
    private Mothership observer;
    
    public SensorStation(){

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
}

