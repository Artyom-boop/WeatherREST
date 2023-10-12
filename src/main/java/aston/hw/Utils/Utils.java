package aston.hw.Utils;

/**
 * The Utils class provides utility methods for common operations.
 */
public class Utils {

    /**
     * Checks whether a given string can be converted to a numeric value.
     *
     * @param str the string to check for numeric conversion
     * @return true if the string is numeric, otherwise false
     */
    public static boolean isNumeric(String str) {
        if (str == null)
            return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
