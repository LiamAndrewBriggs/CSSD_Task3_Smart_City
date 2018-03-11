package SmartCity;

import java.util.ArrayList;
import java.util.UUID;

public class SensorStation {
    
    private String stationID;
    private String stationName;
    private ArrayList<SensorMonitor> sensorMonitors = new ArrayList<>();
    private Mothership observer;
    
    public SensorStation(String name){
        stationID = UUID.randomUUID().toString();
        stationName = name;
    }
    
    public SensorStation(){

    }
    
    public void addNewSensorMonitor(){
        SensorMonitor newStation = new SensorMonitor();
        sensorMonitors.add(newStation);
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
    
    public ArrayList<SensorMonitor> getSensorMonitors(){
        return sensorMonitors;
   }
    
    public SensorMonitor getSensorMonitor(String monitorID)
    {
        SensorMonitor aSensorMonitor = new SensorMonitor(); 
        
        for (SensorMonitor thisStation : sensorMonitors) {
            if(thisStation.getID().equals(monitorID));
            aSensorMonitor = thisStation;
        }
        
        return aSensorMonitor;
    }
    
    public String getName() {
        return stationName;
    }
    
    public String getID() {
        return stationID;
    }
}

