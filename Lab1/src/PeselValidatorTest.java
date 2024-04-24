import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.IllegalFormatWidthException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class PeselValidatorTest {
    private static final Logger LOG = Logger.getLogger(PeselValidatorTest.class.getName());

    @BeforeEach
    void setUp() {
        LOG.info("Test start");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Test end");
    }

    @Test
    void validatePesel() {
        assertTrue(PeselValidator.validatePesel("90010112345")); // Example valid PESEL
        assertFalse(PeselValidator.validatePesel(null)); // Null PESEL
        assertThrows(IndexOutOfBoundsException.class,() -> PeselValidator.validatePesel("")); // Empty PESEL
        assertThrows(IllegalArgumentException.class,() -> PeselValidator.validatePesel("1234567890")); // Invalid length PESEL
        assertThrows(IllegalArgumentException.class,() -> PeselValidator.validatePesel("12345678901")); // Invalid PESEL
    }

    @Test
    void getGender() {
        assertEquals("K", PeselValidator.getGender("90010112345")); // Female PESEL
        assertEquals("M", PeselValidator.getGender("90010112356")); // Male PESEL
    }

    @Test
    void isValidFormat() {
        assertThrows(NullPointerException.class, () -> PeselValidator.isValidFormat(null)); // Null PESEL
        assertFalse(PeselValidator.isValidFormat("")); // Empty PESEL
        assertFalse(PeselValidator.isValidFormat("1234")); // Too short PESEL
        assertFalse(PeselValidator.isValidFormat("123abc123de")); // Non-numeric characters
        assertFalse(PeselValidator.isValidFormat("123abc")); // Non-numeric characters
        assertTrue(PeselValidator.isValidFormat("12345678901")); // Valid PESEL
    }

    @Test
    void getBirthDate() {
        assertEquals("01-01-1990", PeselValidator.getBirthDate("90010112345")); // Example PESEL birth date
        assertThrows(IndexOutOfBoundsException.class, () -> PeselValidator.getBirthDate("12345")); // Too short PESEL
        assertThrows(IllegalArgumentException.class, () -> PeselValidator.getBirthDate("123456789012345")); // Too long PESEL
    }
}
