package SmartCity;
import java.util.ArrayList;

/**
 *
 * @author Dran
 */
public class EmbellishedData {

    /**
     * Has a data object
     */
    public Data data;

    /**
     * Variable for holding the recorded time in milliseconds
     */
    public Long timeInMillis;

    /**
     * A list of locations
     */
    public ArrayList<Double> location;

    /**
     * Variable for holding the Sensor ID
     */
    public String sensorID;
    
    /**
     * Constructor for creating an EmbellishedData object requires data, time in milliseconds,
     * a list of locations and a sensor ID
     * 
     * @param data
     * @param timeInMillis
     * @param location
     * @param sensorID
     */
    public EmbellishedData(Data data, Long timeInMillis, 
                            ArrayList<Double> location, 
                                String sensorID)
    {
        this.data = data;
        this.timeInMillis = timeInMillis;
        this.location = location;
        this.sensorID = sensorID;
    }

}
