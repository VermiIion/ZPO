import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@DefaultStudent(imie = "Adam", nazwisko = "Nowak", nr_albumu = "123456")
public class Student {

    private String imie;
    private String nazwisko;
    private String nr_albumu;
    private ArrayList<Float> oceny;

    public Student() {
        this.imie = "Adam";
        this.nazwisko = "Nowak";
        this.nr_albumu = "1234";
        this.oceny = new ArrayList<>(Arrays.asList(3.0f,4.0f,5.0f));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Method m : this.getClass().getMethods()) {
            if (m.getName().startsWith("get") && m.getParameterCount() == 0 && !m.getName().endsWith("Class")) {
                try {
                    Object res;
                    if ((res = m.invoke(this)) instanceof String) {
                        sb.append(res)
                                .append(", ");
                    } else sb.append(res.toString()).append(", ");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNr_albumu() {
        return nr_albumu;
    }

    public ArrayList<Float> getOceny() {
        return oceny;
    }
}
