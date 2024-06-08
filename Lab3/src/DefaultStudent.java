import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface DefaultStudent {
    String imie() default "Jan";
    String nazwisko() default "Kowalski";
    String nr_albumu() default "000000";
    String[] oceny() default {"3.0", "4.0"};
}