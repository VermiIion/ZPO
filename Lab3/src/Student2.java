import java.util.ArrayList;
import java.util.Arrays;

public class Student2 extends Student{
    private String imie;
    private String nazwisko;
    private String nr_albumu;
    private ArrayList<Float> oceny;
    private int wiek;

    public Student2() {
        this.imie = "Adam";
        this.nazwisko = "Nowak";
        this.nr_albumu = "1234";
        this.oceny = new ArrayList<>(Arrays.asList(3.0f,4.0f,5.0f));
        this.wiek = 24;
    }

    public static void main(String[] args) {
        Student2 student2 = new Student2();
        System.out.println(student2.toString());
    }

    public int getWiek() {
        return wiek;
    }
}
