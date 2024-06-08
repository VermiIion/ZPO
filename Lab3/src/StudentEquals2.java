import java.util.List;

public class StudentEquals2 extends StudentEquals{
    private int wiek;
    public StudentEquals2(String imie, String nazwisko, int indeks, List<Float> oceny, int wiek) {
        super(imie, nazwisko, indeks, oceny);
        this.wiek = wiek;
    }

    public static void main(String[] args) {
        StudentEquals2 student1 = new StudentEquals2("Jan", "Kowalski", 123, List.of(3.0f, 4.0f),24);
        StudentEquals2 student2 = new StudentEquals2("Jan", "Kowalski", 456, List.of(3.5f, 5.0f), 21);
        System.out.println(student1.equals(student2));
        StudentEquals2 student3 = new StudentEquals2("Jan", "Kowalski", 123, List.of(3.0f, 4.0f),21);
        System.out.println(student3.equals(student2));
    }
}
