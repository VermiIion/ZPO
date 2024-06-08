import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
        Arrays.sort(superFields, Comparator.comparingInt(f -> {
            if (f.isAnnotationPresent(DisplayAnno.class)) {
                return f.getAnnotation(DisplayAnno.class).order();
            }
            return Integer.MAX_VALUE;
        }));

        buildString(sb, superFields);

        buildString(sb, fields);

        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    private void buildString(StringBuilder sb, Field[] superFields) {
        for (Field field : superFields) {
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
    }

    public static void main(String[] args) {
        StudentToString student = new StudentToString("Jan", "Kowalski", 123456, Arrays.asList(3.0f, 4.0f, 5.0f));
        System.out.println(student);
    }

    public int getIndeks() {
        return indeks;
    }

    public List<Float> getOceny() {
        return oceny;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
