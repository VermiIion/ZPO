import java.lang.reflect.Field;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Objects;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface IgnoreEquals {
}
public class StudentEquals {



    @IgnoreEquals
    private int indeks;

    @IgnoreEquals
    private List<Float> oceny;

    private String imie;
    private String nazwisko;

    public StudentEquals(String imie, String nazwisko, int indeks, List<Float> oceny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.indeks = indeks;
        this.oceny = oceny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEquals student = (StudentEquals) o;

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IgnoreEquals.class)) continue;
            field.setAccessible(true);
            try {
                Object thisValue = field.get(this);
                Object otherValue = field.get(student);
                if (!Objects.equals(thisValue, otherValue)) return false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public static void main(String[] args) {
        StudentEquals student1 = new StudentEquals("Jan", "Kowalski", 123, List.of(3.0f, 4.0f));
        StudentEquals student2 = new StudentEquals("Jan", "Kowalski", 456, List.of(3.5f, 5.0f));
        System.out.println(student1.equals(student2));
    }
}
