import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Calculator {

    public static double plus(double a, double b) {
        return a + b;
    }

    public static double minus(double a, double b) {
        return a - b;
    }

    public static double razy(double a, double b) {
        return a * b;
    }

    public static double dziel(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public static double pierwiastek(double a) {
        if (a < 0) {
            throw new ArithmeticException("Cannot take the square root of a negative number");
        }
        return Math.sqrt(a);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression:");
        String input = scanner.nextLine();
        scanner.close();

        try {
            double result = processInput(input);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static double processInput(String input) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ArithmeticException {
        String[] tokens = input.split(" ");
        if (tokens.length == 2) {
            String operation = tokens[0];
            double operand = Double.parseDouble(tokens[1]);
            Method method = Calculator.class.getMethod(operation, double.class);
            return (double) method.invoke(null, operand);
        } else if (tokens.length == 3) {
            double operand1 = Double.parseDouble(tokens[0]);
            String operation = tokens[1];
            double operand2 = Double.parseDouble(tokens[2]);
            Method method = Calculator.class.getMethod(operation, double.class, double.class);
            return (double) method.invoke(null, operand1, operand2);
        } else {
            throw new IllegalArgumentException("Invalid input format");
        }
    }
}