package SmartCity;

import java.util.ArrayList;

public class Mothership {
    //private {id} ID;
    private ArrayList<SensorStation> sensorStations = new ArrayList<>();
    
    public void addNewSensorStation()
    {
        SensorStation newStation = new SensorStation("test");
        sensorStations.add(newStation);
        
        newStation = new SensorStation("abc");
        sensorStations.add(newStation);
    }
    
    public void removeSensorStation()
    {
        //todo
    }
    
    public SensorStation getSensorStation(String sensorID)
    {
        SensorStation aSensorStation = new SensorStation(); 
        
        for (SensorStation thisStation : sensorStations) {
            if(thisStation.getStationID().equals(sensorID));
            aSensorStation = thisStation;
        }
        
        return aSensorStation;
    }
    
    public ArrayList<SensorStation> getSensorStations()
    {
        return sensorStations;
    }
    
    public void receiveStationData(EmbellishedData data)
    {
        //todo
    }
}
