package SmartCity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Dran
 */
public class SensorStation {
    private ArrayList<Double> coords = new ArrayList<>();
    
    private Mothership observer;
    private ArrayList<SensorMonitor> sensorMonitors = new ArrayList<>();
    private String stationID;
    private String stationName;
    
    /**
     *
     */
    public SensorStation(){
        
    }
    
    public SensorStation(String name, Double latitude, Double longitude) {
       stationID =  UUID.randomUUID().toString();
       stationName = name;
       coords.add(latitude);
       coords.add(longitude);
    }
    
    /**
     * Constructor for a Sensor Station object
     * Requires an ID, name, latitude and longitude
     * @param id
     * @param name
     * @param latitude
     * @param longitude
     */
    public SensorStation(Integer id, String name, Double latitude, Double longitude) {
       stationID =  id.toString();
       stationName = name;
       coords.add(latitude);
       coords.add(longitude);
       
       
        Connection con = UserInterface.getConnection();
        
        Statement st;
        ResultSet rs;
        SensorMonitor sensorMonitor;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM SENSORMONITORS WHERE STATIONID=" + Integer.parseInt(stationID) + "");
            
            while(rs.next()){
                sensorMonitor = new SensorMonitor(
                    rs.getInt("MonitorID"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getDouble("Frequency"),
                    coords
                );
                sensorMonitors.add(sensorMonitor);
            }
            
        } catch (SQLException ex){
            
        }
    }
    
    /**
     * Adds a Sensor Monitor to the list of Sensor Monitors a Sensor Station has
     * @param newStation
     */
    public void addSensorMonitor(SensorMonitor newStation){
        sensorMonitors.add(newStation);
        
        newStation.registerObserver(this);
    }
    
    /**
     * @return the coords
     */
    public ArrayList<Double> getCoords() {
        return coords;
    }
    
    /**
     * @param coords the coords to set
     */
    public void setCoords(ArrayList<Double> coords) {
        this.coords = coords;
    }

    /**
     * @return the observer
     */
    public Mothership getObserver() {
        return observer;
    }

    /**
     * @param observer the observer to set
     */
    public void setObserver(Mothership observer) {
        this.observer = observer;
    }
    
    /**
     * Get a Sensor Monitor from the 
     * @param monitorID
     * @return
     */
    public SensorMonitor getSensorMonitor(String monitorID)
    {
        SensorMonitor aSensorMonitor = new SensorMonitor();
        
        for (SensorMonitor thisStation : getSensorMonitors()) {
            if(thisStation.getSensorMonitorID().equals(monitorID)) {
                aSensorMonitor = thisStation;
            }
        }
        
        return aSensorMonitor;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<SensorMonitor> getSensorMonitors(){
        return sensorMonitors;
    }

    /**
     * @param sensorMonitors the sensorMonitors to set
     */
    public void setSensorMonitors(ArrayList<SensorMonitor> sensorMonitors) {
        this.sensorMonitors = sensorMonitors;
    }

    /**
     * @return the stationID
     */
    public String getStationID() {
        return stationID;
    }

    /**
     * @param stationID the stationID to set
     */
    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    /**
     * @return the stationName
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName the stationName to set
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    /**
     *
     * @param temp
     * @return
     */
    public PublicInterface receiveSensorData(EmbellishedData temp){
        PublicInterface temp2 = new PublicInterface();
        
        return temp2;
    }

    /**
     *
     * @param temp
     */
    public void registerObserver(Mothership temp){
        
    }
    
    /**
     * Removes a Sensor Monitor
     */
    public void removeSensorMonitor(){
        
    }
    
    /**
     * Unregisters a Mothership observer
     * @param temp
     */
    public void unregisterObserver(Mothership temp){
        
    }
    
    /**
     *  Updates a Sensor's frequency
     */
    public void updateSensorFrequency(){
        
    }
    
    public void receiveEmbellishedData(EmbellishedData data){
        observer.receiveStationData(data);
    }
   
}