/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Peter Perri
 */
import java.text.NumberFormat;

public class InventoryItem {

    private String name;
    private String value;
    private String serialNumber;

    public InventoryItem(String name, String value, String serialNumber){
        //constructor is only called if name, value, and serialNumber are validated
        //before the constructor call

        //if value is empty, set value to 0
        if(value.isEmpty()){
            value = "0";
        }

        //format what the user entered as currency
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        value = removeDollarSign(value);
        float valueFloat = Float.parseFloat(value);

        this.name = name;
        this.value = formatter.format(valueFloat);
        this.serialNumber = serialNumber;
    }

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public boolean setName(String name) {
        if (nameIsValid(name)) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean setValue(String value) {
        //if they entered nothing, value is 0
        if(value.isEmpty()){
            value = "0";
        }
        if (valueIsValid(value)) {
            //format value as currency
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            value = removeDollarSign(value);
            float valueFloat = Float.parseFloat(value);
            this.value = formatter.format(valueFloat);
            return true;
        } else {
            return false;
        }
    }

    public boolean setSerialNumber(String serialNumber) {
        if (serialNumberIsValid(serialNumber)) {
            this.serialNumber = serialNumber;
            return true;
        } else {
            return false;
        }
    }

    public static boolean nameIsValid(String name){
        //if the name is between 2 and 256 characters in length (inclusive)
        //return true, otherwise return false
        return name.length() >= 2 && name.length() <= 256;
    }

    public static boolean valueIsValid(String value){
            //if the value is empty, then it is valid. Will be set to 0 elsewhere.
            if(value.isEmpty()){
                return true;
            }
            value = removeDollarSign(value);
            //if the value is not negative, return true
            try {
                float parsedFloat = Float.parseFloat(value);
                return parsedFloat >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
    }

    public static boolean serialNumberIsValid(String serialNumber){
        //if the serial number is in the format of A-XXX-XXX-XXX where
        //A must be a letter and X can be either a letter or digit, return true

        //if the length is not 13, return false
        if(serialNumber.length() != 13){
            return false;
        }
        char[] charArray = serialNumber.toCharArray();

        //if the first character is not a letter, return false
        if(!(Character.isAlphabetic(charArray[0]))){
            return false;
        }

        //if characters at index 1, 5, and 9 are not all '-', return false
        for(int i = 1; i <= 9; i+=4){
            if (charArray[i] != '-'){
                return false;
            }
        }

        //if a character at any index other than 1, 5, and 9 are anything other than a letter or number, return false
        for(int i = 0; i < 13; i++){
            if(i == 1 || i == 5 || i == 9){
                continue;
            }
            if(!(Character.isDigit(charArray[i]) || Character.isAlphabetic(charArray[i]))){
                return false;
            }
        }
        return true;
    }

    private static String removeDollarSign(String value){
        if(value.contains("$")){
            value = value.replace("$", "");
        }
        return value;
    }

}
