package SmartCity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
//        Connection con = UserInterface.getConnection();
//        
//        Statement st;
//        ResultSet rs;
//        SensorStation sensorStation;
//        
//        try{
//            st = con.createStatement();
//            rs = st.executeQuery("SELECT * FROM SENSORSTATIONS");
//            
//            while(rs.next()){
//                sensorStation = new SensorStation(
//                    rs.getString("stationname"),
//                    rs.getDouble("latitude"),
//                    rs.getDouble("longitude")
//                );
//                sensorStations.add(sensorStation);
//            }
//            
//        } catch (SQLException ex){
//            
//        }
        
        return sensorStations;
    }
    
    public void receiveStationData(EmbellishedData data)
    {
        //todo
    }
}
