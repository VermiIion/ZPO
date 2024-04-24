import java.security.InvalidParameterException;
import java.util.regex.Pattern;


public class PeselValidator {
    public static boolean validatePesel(String pesel) throws NullPointerException, IndexOutOfBoundsException{
        try{
            isValidFormat(pesel);
            getBirthDate(pesel);
            getGender(pesel);
        }catch(NullPointerException e) {
            System.out.println("PESEL not valid");
            return false;
        }
        return true;
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

    private static String getFullYear(String year) {
        int prefix = Integer.parseInt(year);
        if (prefix >= 0 && prefix <= 99) {
            return prefix <= 19 ? "20" + year : "19" + year;
        } else {
            throw new IllegalArgumentException("Invalid year prefix in PESEL");
        }
    }

    private static String getFullMonth(String month) {
        int prefix = Integer.parseInt(month);

        if (prefix >= 1 && prefix <= 12) {
            return (prefix < 10) ? "0" + prefix : String.valueOf(prefix);
        } else {
            throw new IllegalArgumentException("Invalid month prefix in PESEL");
        }
    }
}