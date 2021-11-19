public class InventoryItem {

    private String name;
    private float value;
    private String serialNumber;

    public InventoryItem(String name, float value, String serialNumber){
        //constructor is only called if name, value, and serialNumber are validated
        //before the constructor call
        this.name = name;
        this.value = value;
        this.serialNumber = serialNumber;
    }

    public String getName(){
        return name;
    }

    public float getValue(){
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

    public boolean setValue(float value) {
        if (valueIsValid(value)) {
            //round the value to the nearest cent; only display value with two digits
            this.value = value;
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
        return true;
    }

    public static boolean valueIsValid(float value){
        //if the value is not negative, return true
        return true;
    }

    public static boolean serialNumberIsValid(String serialNumber){
        //if the serial number is in the format of A-XXX-XXX-XXX where
        //A must be a letter and X can be either a letter or digit, return true
        return true;
    }

}
