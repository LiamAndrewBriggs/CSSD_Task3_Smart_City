package SmartCity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dran
 */
public class Mothership {
    //private {id} ID;
    private ArrayList<SensorStation> sensorStations = new ArrayList<>();
    
    /**
     * Constructor for a Mothership instance
     * Connects to the Java DB to fetch Sensor Stations data
     */
    public Mothership() {
        Connection con = UserInterface.getConnection();
        
        Statement st;
        ResultSet rs;
        SensorStation sensorStation;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM SENSORSTATIONS");
            
            while(rs.next()){
                sensorStation = new SensorStation(
                    rs.getInt("StationID"),
                    rs.getString("stationname"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")
                );
                sensorStations.add(sensorStation);
            }
            
        } catch (SQLException ex){
            
        }
    }
    
    /**
     * Adds a new Sensor Station
     * @param newStation
     */
    public void addNewSensorStation(SensorStation newStation)
    {
        sensorStations.add(newStation);
    }
    
    /**
     * Returns a Sensor Station object
     * @param sensorID
     * @return
     */
    public SensorStation getSensorStation(String sensorID)
    {
        SensorStation aSensorStation = new SensorStation(); 
        
        for (SensorStation thisStation : sensorStations) {
            if(thisStation.getStationID().equals(sensorID))
            {
                aSensorStation = thisStation;
            }
        }
        
        return aSensorStation;
    }
    
    /**
     * Returns a list of Sensor Stations
     * @return
     */
    public ArrayList<SensorStation> getSensorStations()
    {
        return sensorStations;
    }
    
    /**
     * 
     * @param data
     */
    public void receiveStationData(EmbellishedData data)
    {
        //todo
    }

    /**
     *
     */
    public void removeSensorStation(SensorStation oldStation)
    {
        sensorStations.remove(oldStation);
    }
}
