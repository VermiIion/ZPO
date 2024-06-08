import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface DisplayAnno {
    String comment();
    int order();
}


public class StudentToString {

    @DisplayAnno(comment = "numer indeksu", order = 2)
    private int indeks;

    @DisplayAnno(comment = "Lista ocen", order = 3)
    private List<Float> oceny;

    private String imie;

    @DisplayAnno(comment = "Nazwisko", order = 1)
    private String nazwisko;

    public StudentToString(String imie, String nazwisko, int indeks, List<Float> oceny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.indeks = indeks;
        this.oceny = oceny;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        Arrays.sort(fields, Comparator.comparingInt(f -> {
            if (f.isAnnotationPresent(DisplayAnno.class)) {
                return f.getAnnotation(DisplayAnno.class).order();
            }
            return Integer.MAX_VALUE;
        }));

        for (Field field : fields) {
            if (field.isAnnotationPresent(DisplayAnno.class)) {
                DisplayAnno annotation = field.getAnnotation(DisplayAnno.class);
                field.setAccessible(true);
                try {
                    sb.append(annotation.comment())
                            .append(": ")
                            .append(field.get(this))
                            .append(", ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove trailing ", "
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StudentToString student = new StudentToString("Jan", "Kowalski", 123456, Arrays.asList(3.0f, 4.0f, 5.0f));
        System.out.println(student);
    }
}
