/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartCity;

/**
 * Sensor class
 * Superclass for the types of sensors which are are concrete classes
 * @author Dran
 */
public class Sensor {
    
    private String sensorID;
    
    /**
     * Constructor for a Sensor object
     */
    public Sensor(){
        
    }
    
    /**
     * Returns data
     * @return
     */
    public Data getData() {
        Data temp = new Data();
        
        return temp;
    }
    
    public String getID(){
        return sensorID;
    }
}
