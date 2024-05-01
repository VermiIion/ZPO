import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
        assertTrue(PeselValidator.validatePesel("90010112349")); // Example valid PESEL
        assertFalse(PeselValidator.validatePesel(null)); // Null PESEL
        assertFalse(PeselValidator.validatePesel("")); // Empty PESEL
        assertFalse(PeselValidator.validatePesel("1234567890")); // Invalid length PESEL
        assertFalse(PeselValidator.validatePesel("12345678901")); // Invalid PESEL
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
    void getBirthDate() {                                                  //9 9 3 7 8 3 2 5
        assertEquals("01-01-1990", PeselValidator.getBirthDate("90010112345")); // Example PESEL birth date
        assertThrows(IndexOutOfBoundsException.class, () -> PeselValidator.getBirthDate("12345")); // Too short PESEL
        assertThrows(IllegalArgumentException.class, () -> PeselValidator.getBirthDate("123456789012345")); // Too long PESEL
    }

    @Test
    void calculateChecksum() {
        assertEquals(8, PeselValidator.calculateChecksum("02070803628")); // Example PESEL with checksum 5
        assertEquals(7, PeselValidator.calculateChecksum("82030498767")); // Example PESEL with checksum 3
        assertThrows(IllegalArgumentException.class, ()->PeselValidator.calculateChecksum("123456"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tests.csv", numLinesToSkip = 1)
    void validatePeselFromFile(String pesel, String gender, String birthDate, String valid, String checksum) {
        boolean expectedValidity = Boolean.parseBoolean(valid);
        boolean actualValidity = PeselValidator.validatePesel(pesel);

        assertEquals(expectedValidity, actualValidity, "Validation result for PESEL: " + pesel);

        if (expectedValidity) {
            assertEquals(gender, PeselValidator.getGender(pesel), "Gender for PESEL: " + pesel);
            assertEquals(birthDate, PeselValidator.getBirthDate(pesel), "Birth date for PESEL: " + pesel);
            assertEquals(Integer.parseInt(checksum), PeselValidator.calculateChecksum(pesel), "Checksum for PESEL: " + pesel);
        }
    }
}
