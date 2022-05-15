package tests;

import math.Convert;
import math.ConvertException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests of public methods
 * @author Marek
 * @version 2.0
 */
public class ConvertTest {
    
    Convert convert;
    
    @BeforeEach
    public void setUp() {
        convert = new Convert();
    }
    
    /**
     * Test if the conversion is correct
     * @param input roman or arabic number
     * @param expected converted number
     */
    @ParameterizedTest
    @CsvSource({"1,I", "10,X", "126,CXXVI", "3274,MMMCCLXXIV", "3999,MMMCMXCIX", "I,1", "X,10", "CXXVI,126", "MMMCCLXXIV,3274", "MMMCMXCIX,3999"})
    public void testConversion(String input, String expected){
        try {
            assertEquals(convert.conversion(input), expected, "Conversion went wrong or wrong expected value");
        } catch (ConvertException e) {
            fail("Input string is not a roman or arabic number in range 1 - 3999");
        }
    }   
    
   /**
    * Test filtering of strings that are not a valid roman or arabic number from range 1 - 3999
    * @param parameter not valid roman or arabic number
    */
    @ParameterizedTest
    @ValueSource(strings = {"-236", "0", "34727", "DXDM", "-X", "xl0", "4000", "5hdsd", "MMMM", " ", ""})
    public void testConvertException(String parameter) {
        try {
            convert.conversion(parameter);
            fail("An exception should be thrown when the string is not a roman or arabic number in range 1 - 3999");
        } catch (ConvertException e) {
        }
    }
    
    /**
     * Test null detection in conversion from roman or arabic number
     */
    @Test
    public void testConvertNullException() {
        try {
            convert.conversion(null);
            fail("An exception should be thrown when there is null");
        } catch (ConvertException e) {
        }
    }

}
