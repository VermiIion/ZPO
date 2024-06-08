import java.util.Arrays;
import java.util.List;

public class StudentToString2 extends StudentToString{
    @DisplayAnno(comment = "wiek", order = 5)
    private int wiek;
    public StudentToString2(String imie, String nazwisko, int indeks, List<Float> oceny, int wiek) {
        super(imie, nazwisko, indeks, oceny);
        this.wiek = wiek;
    }

    public static void main(String[] args) {
        StudentToString2 student = new StudentToString2("Jan", "Kowalski", 123456, Arrays.asList(3.0f, 4.0f, 5.0f),24);
        System.out.println(student);
    }
}
