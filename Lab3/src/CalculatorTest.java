import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testPlus() throws Exception {
        assertEquals(15.0, Calculator.processInput("9 plus 6"), 0.001);
    }

    @Test
    public void testMinus() throws Exception {
        assertEquals(-3.0, Calculator.processInput("2 minus 5"), 0.001);
    }

    @Test
    public void testRazy() throws Exception {
        assertEquals(12.0, Calculator.processInput("3 razy 4"), 0.001);
    }

    @Test
    public void testDziel() throws Exception {
        assertEquals(2.5, Calculator.processInput("5 dziel 2"), 0.001);
    }

    @Test
    public void testPierwiastek() throws Exception {
        assertEquals(2.0, Calculator.processInput("pierwiastek 4"), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() throws Exception {
        Calculator.processInput("invalid input");
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() throws Exception {
        Calculator.processInput("5 dziel 0");
    }

    @Test(expected = ArithmeticException.class)
    public void testNegativeSquareRoot() throws Exception {
        Calculator.processInput("pierwiastek -9");
    }
}
