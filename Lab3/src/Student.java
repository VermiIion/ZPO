import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@DefaultStudent(imie = "Adam", nazwisko = "Nowak", nr_albumu = "123456")
public class Student {

    private String imie;
    private String nazwisko;
    private String nr_albumu;
    private String[] oceny;

    public Student() {
        if (this.getClass().isAnnotationPresent(DefaultStudent.class)) {
            DefaultStudent defaultStudent = this.getClass().getAnnotation(DefaultStudent.class);
            this.imie = defaultStudent.imie();
            this.nazwisko = defaultStudent.nazwisko();
            this.nr_albumu = defaultStudent.nr_albumu();
            this.oceny = defaultStudent.oceny();
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", nr_albumu='" + nr_albumu + '\'' +
                ", oceny=" + String.join(", ", oceny) +
                '}';
    }


    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);
    }
}
