package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class that converts an arabic number to roman or roman to arabic
 * @author Marek Michali
 * @version 4.0
 */
public class Convert {    
    
    /**
     * Contains the history of operations
     */
    private ArrayList<String> history = new ArrayList<String>();
  
    /**
    *  Posibble roman numerals
    */
     private enum RomanNumeral{
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
        int weight;
        
        RomanNumeral(int weight) {
            this.weight = weight;
        }
        
        private int getValue() {
            return weight;
        }
    }   
     
     /**
     * Return the history of operations
     * @return history of operations
     */
    public ArrayList<String> getHistory(){
        return history;
    }
    
    /**
     * Checks if given string is an arabic or roman number and converts it
     * @param number number to convert
     * @return converted number
     * @throws ConvertException when the number is null or not roman or arabic
     */
    public String conversion(String number) throws ConvertException{
        if(number == null){
            throw new ConvertException("Error! null is not a valid value");
        }
        number = number.toUpperCase();
        Pattern romanArabic = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$|^([1-9][0-9]{0,2}|[1-3][0-9]{0,3})$");
        if(romanArabic.matcher(number).find() == false || number.isEmpty()){
            throw new ConvertException("Error! This is not a valid roman or arabic number");
        }
        if(this.romanValidation(number) == true){
            String convertedNumber = this.convertToArabic(number);
            history.add(number);
            history.add(convertedNumber);
            return convertedNumber;
        }
        else if(this.arabicValidation(number) == true){
            String convertedNumber = this.convertToRoman(number);
            history.add(number);
            history.add(convertedNumber);
            return convertedNumber;
        }       
        return null;
    }
    /**
     * Checks if the roman number is correct
     * @param romanNumber roman number to check
     * @return information if this is a correct roman number
     */
    private boolean romanValidation(String romanNumber){
        Pattern romanPattern = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        return romanPattern.matcher(romanNumber).find();
      
    }
    /**
     * Checks if the arabic number is correct and is in range from 1 to 3999
     * @param arabicNumber arabic number to check
     * @return information if this is a arabic number possible to convert
     */
    private boolean arabicValidation(String arabicNumber){
        Pattern arabicPattern = Pattern.compile("^([1-9][0-9]{0,2}|[1-3][0-9]{0,3})$");    
        return arabicPattern.matcher(arabicNumber).find();
    }
    
    /**
     * Converts a roman number to arabic
     * @param romanNumber roman numer to convert
     * @return converted number
     */
    private String convertToArabic(String romanNumber){
        List<Character> romanDigits = romanNumber.chars()
            .mapToObj(p->(char)p)
            .collect(Collectors.toList());
        int previousConvertedDigit=0;
        int convertedNumber=0;
        int convertedDigit=20000;
        for(char numeral: romanDigits){
            for(RomanNumeral symbol: RomanNumeral.values()) {
                if(symbol.toString().equals(String.valueOf(numeral))){
                    convertedDigit = symbol.getValue();
                    break;
                }            
            }
            convertedNumber += convertedDigit;
            if(convertedDigit > previousConvertedDigit){
                convertedNumber -= 2*previousConvertedDigit;
            }
            previousConvertedDigit = convertedDigit;
        }
        String convertedNumberStr = String.valueOf(convertedNumber);
        return convertedNumberStr;
    }
    /**
     * Converts an arabic number to roman
     * @param arabicNumber arabic number to convert
     * @return converted number
     */
    private String convertToRoman(String arabicNumber){
        int arabicIntNumber = 0;
        arabicIntNumber = Integer.parseInt(arabicNumber);
        StringBuilder stringBuilder = new StringBuilder();
        
        Map<Integer, String> romanSymbol = new HashMap<>();
        romanSymbol.put(1000,"M");
        romanSymbol.put(900,"CM");
        romanSymbol.put(500,"D");
        romanSymbol.put(400,"CD");
        romanSymbol.put(100,"C");
        romanSymbol.put(90,"XC");
        romanSymbol.put(50,"L");
        romanSymbol.put(40,"XL");
        romanSymbol.put(10,"X");
        romanSymbol.put(9,"IX");
        romanSymbol.put(5,"V");
        romanSymbol.put(4,"IV");
        romanSymbol.put(1,"I");

        while(arabicIntNumber >= 1000){
            arabicIntNumber-=1000;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 1000)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=900){
            arabicIntNumber-=900;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 900)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=500){
            arabicIntNumber-=500;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 500)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=400){
            arabicIntNumber-=400;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 400)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        while(arabicIntNumber>=100){
            arabicIntNumber-=100;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 100)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=90){
            arabicIntNumber-=90;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 90)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=50){
            arabicIntNumber-=50;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 50)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=40){
            arabicIntNumber-=40;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 40)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        while(arabicIntNumber>=10){
            arabicIntNumber-=10;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 10)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=9){
            arabicIntNumber-=9;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 9)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=5){
            arabicIntNumber-=5;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 5)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        if(arabicIntNumber>=4){
            arabicIntNumber-=4;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 4)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        while(arabicIntNumber>=1){
            arabicIntNumber-=1;
            stringBuilder.append(romanSymbol.entrySet().stream()
                .filter(map -> map.getKey() == 1)
                .map(map -> map.getValue())
                .collect(Collectors.joining()));
        }
        String convertedNumber = stringBuilder.toString();
        return convertedNumber;    
    }
}


