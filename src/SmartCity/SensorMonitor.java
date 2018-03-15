package SmartCity;

/**
 *
 * @author User
 */
import java.util.*;

/**
 * Sensor Monitor class
 * Contains details of a Sensor Monitor including interval, status (active/not active), last reading time,
 * and has a Sensor Station as an observer
 * @author Dran
 */
public class SensorMonitor {
    
    private Double interval;
    private Boolean isActive;
    private Long lastReadingTime;
    private SensorStation observer;
    private ArrayList<Double> coords;
    private EmbellishedData embellishedData;
    /**
     * Has a data object
     */
    private Data reading;
    /**
     * Has a readings count for data gathering
     */
    private Integer readingsCount;

    private Sensor sensor;
    private String sensorMonitorID;
    
    /**
     * Constructor for a Sensor Monitor object with no arguments
     */
    public SensorMonitor()
    {
        
    }
    
    /**
     * Constructor for a Sensor Monitor object
     * Requires an object for status (active/not active), interval and an object for description
     * @param newIsActive
     * @param newIsInterval
     * @param newDesc
     */
    public SensorMonitor(Object newIsActive, double newIsInterval, Object newDesc)
    {
        sensorMonitorID = UUID.randomUUID().toString();
        interval = newIsInterval;
        
        if (newIsActive.equals("Active")) {
            isActive = true;
        } else {
            isActive = false;
        }
        
        if (newDesc.equals("Bin Sensor")) {
            sensor = new BinSensor();
        } else if (newDesc.equals("Flood Sensor")) {
            sensor = new FloodSensor();
        } else {
            sensor = new TrafficSensor();
        }
        
        reading = new Data();
    }
    
    /**
     * Constructor for a Sensor Monitor object
     * Requires an ID, description and a frequency
     * @param id
     * @param desc
     * @param status
     * @param frequency
     */
    public SensorMonitor(int id, String desc, String status, double frequency, ArrayList<Double> coords)
    {
        sensorMonitorID = Integer.toString(id);
        
        if (desc.equals("Bin Sensor")) {
            sensor = new BinSensor();
        } else if (desc.equals("Flood Sensor")) {
            sensor = new FloodSensor();
        } else {
            sensor = new TrafficSensor();
        }
        
        if (status.equals("Active")) {
            isActive = true;
        } else {
            isActive = false;
        }
        
        interval = frequency;
        
        Clock clock = Clock.getInstance();
        clock.registerObserver(this);
    }
    /**
     *
     */
    public void doTick() {
        interval--;
        
        if (interval == 0){
            getReadingsCount();
        }
    }
    
    /**
     * Sets the object's frequency
     * @param frequency
     */
    public void setFrequency(String frequency) {
        setInterval((Double) Double.parseDouble(frequency));
    }
    
    /**
     * 
     * @return
     */
    public Double getInterval() {
        return interval;
    }
    

    /**
     * @param interval the interval to set
     */
    public void setInterval(Double interval) {
        this.interval = interval;
    }

    /**
     * @return the isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the lastReadingTime
     */
    public Long getLastReadingTime() {
        return lastReadingTime;
    }

    /**
     * @param lastReadingTime the lastReadingTime to set
     */
    public void setLastReadingTime(Long lastReadingTime) {
        this.lastReadingTime = lastReadingTime;
    }

    /**
     * @return the observer
     */
    public SensorStation getObserver() {
        return observer;
    }

    /**
     * @param observer the observer to set
     */
    public void setObserver(SensorStation observer) {
        this.observer = observer;
    }
    /**
     * @return the reading
     */
    public void shouldTakeReading() {
        reading = sensor.getData();
        embellishData();
        shouldNotifySensorStation();
    }
    /**
     * @param reading the reading to set
     */
    public void setReading(Data reading) {
        this.reading = reading;
    }
    /**
     * @return the readingsCount
     */
    public Integer getReadingsCount() {
        return readingsCount;
    }
    /**
     * @param readingsCount the readingsCount to set
     */
    public void setReadingsCount(Integer readingsCount) {
        this.readingsCount = readingsCount;
    }
    /**
     *
     * @return
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * @param sensor the sensor to set
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * @return the sensorMonitorID
     */
    public String getSensorMonitorID() {
        return sensorMonitorID;
    }

    /**
     * @param sensorMonitorID the sensorMonitorID to set
     */
    public void setSensorMonitorID(String sensorMonitorID) {
        this.sensorMonitorID = sensorMonitorID;
    }
    /**
     *
     */
    public void pollData()
    {
        
    }
    /**
     *
     * @param newObserver
     */
    public void registerObserver(SensorStation newObserver) {
        
    }
    
    public EmbellishedData embellishData(){
        Random r = new Random();
        long timeInMills = r.nextInt(100); 
        ArrayList<Double> coords = getCoords();
        String id = sensor.getID();
        EmbellishedData embellishedData = new EmbellishedData(reading, timeInMills, coords, sensor.getID());
        
        return embellishedData;
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
    
    private void shouldNotifySensorStation(){
        observer.receiveSensorData(embellishedData);
    }
    
    public Data getReading(){
        return reading;
    }
}
