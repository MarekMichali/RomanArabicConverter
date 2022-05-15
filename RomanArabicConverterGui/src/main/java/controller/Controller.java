package controller;

import math.Convert;
import math.ConvertException;

/**
 * Controller of the application
 *
 * @author Marek
 * @version 3.0
 */
public class Controller {
    /**
     * Users input
     */
    private String number;
    public Controller(String number){
        this.number=number;
    }
    public Controller(){}
    /**
     * Tries to convert the roman or arabic number passed by the user
     * 
     * @return converted number or exception message
     */
    public String getConverted(){
        Convert convert = new Convert();
        try {
            return convert.conversion(number);     
        } catch (ConvertException e) {
            return e.getMessage();
        }
        
    }
}
