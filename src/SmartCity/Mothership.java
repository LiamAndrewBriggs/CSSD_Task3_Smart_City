package SmartCity;

import java.util.ArrayList;

public class Mothership {
    //private {id} ID;
    private ArrayList<SensorStation> sensorStations = new ArrayList<>();
    
    public void addNewSensorStation()
    {
        SensorStation newStation = new SensorStation("test");
        sensorStations.add(newStation);
    }
    
    public void removeSensorStation()
    {
        //todo
    }
    
    public void getSensorStation()
    {
        //todo
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
