/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartCity;
import java.util.ArrayList;

/**
 *
 * @author Dran
 */
public class Location {
    
    private double latitude;
    private double longitude;

    public ArrayList<Double> getCoords() {
        ArrayList<Double> coords = new ArrayList<>();
        
        coords.add(latitude);
        coords.add(longitude);
        
        return coords;
    }
    
    public void toAddress(){
        
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
