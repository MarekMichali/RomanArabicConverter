package math;

/**
 * Exception when the input string is not a correct arabic or roman number
 * @author Marek Michali
 * @version 2.0
 */
public class ConvertException extends Exception{
    /**
     * ConvertException constructor
     * 
     * @param message exception message
     */
    public ConvertException(String message) {
        super(message);
    }
}
