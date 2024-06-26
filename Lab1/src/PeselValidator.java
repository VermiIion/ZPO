import java.security.InvalidParameterException;
import java.util.regex.Pattern;


public class PeselValidator {
    public static boolean validatePesel(String pesel) throws NullPointerException, IndexOutOfBoundsException, NumberFormatException{
        try{
            getBirthDate(pesel);
            getGender(pesel);
        return (isValidFormat(pesel) && calculateChecksum(pesel) == Integer.parseInt(String.valueOf(pesel.charAt(pesel.length() - 1))));
        }catch(Exception e) {
            System.out.println("PESEL not valid");
            return false;
        }
    }

    public static String getGender(String pesel) {
        if (pesel.charAt(pesel.length() - 2) % 2 == 0) return "K";
        else return "M";
    }

    public static boolean isValidFormat(String pesel) throws NullPointerException{
            return Pattern.matches("[0-9]{11}", pesel);

    }

    public static String getBirthDate(String pesel) throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException {
        String year = pesel.substring(0, 2);
        String month = pesel.substring(2, 4);
        String day = pesel.substring(4, 6);
        String fullYear = getFullYear(year);
        String fullMonth = getFullMonth(month);
        return day + "-" + fullMonth + "-" + fullYear;
    }

    private static String getFullYear(String year) throws NumberFormatException, NullPointerException, IndexOutOfBoundsException {
        int prefix = Integer.parseInt(year);
        if (prefix >= 0 && prefix <= 99) {
            return prefix <= 19 ? "20" + year : "19" + year;
        } else {
            throw new IllegalArgumentException("Invalid year prefix in PESEL");
        }
    }

    private static String getFullMonth(String month) throws NumberFormatException, NullPointerException, IndexOutOfBoundsException{
        int prefix = Integer.parseInt(month);

        if (prefix >= 1 && prefix <= 12) {
            return (prefix < 10) ? "0" + prefix : String.valueOf(prefix);
        } else {
            throw new IllegalArgumentException("Invalid month prefix in PESEL");
        }
    }

    public static int calculateChecksum(String pesel) throws IllegalArgumentException {
        if (!isValidFormat(pesel)) {
            throw new IllegalArgumentException("Invalid PESEL format");
        }

        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }
        int checksum;
        if(sum > 10) {
            checksum = 10 - (sum % 10);
        }
        else checksum = 10 - sum;
        return checksum == 10 ? 0 : checksum;
    }

}