import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String pesel;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("PESEL: ");
            pesel = scanner.next();
            System.out.println(PeselValidator.validatePesel(pesel));
        }catch(Exception e){
            System.out.println("niewłaściwy PESEL");
        }
    }

}