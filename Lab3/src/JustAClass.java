import java.lang.reflect.Method;

public class JustAClass {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    try {
                        Object value = method.invoke(this);
                        String fieldName = method.getName().substring(3);
                        sb.append(fieldName).append("=").append(value).append("\t");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return sb.toString();
    }
}
