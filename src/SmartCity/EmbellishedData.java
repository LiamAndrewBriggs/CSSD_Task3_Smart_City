package SmartCity;
import java.util.ArrayList;

public class EmbellishedData {
    public Data data;
    public Long timeInMillis;
    public ArrayList<Double> location;
    public String sensorID;
    
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
