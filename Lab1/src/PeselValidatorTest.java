import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    }

    @Test
    void getGender() {
    }

    @Test
    void isValidFormat() {
        assertThrows(NullPointerException.class ,()-> PeselValidator.isValidFormat(null));
        assertFalse(PeselValidator.isValidFormat("1234"));
        assertFalse(PeselValidator.isValidFormat("123abc123de"));
        assertFalse(PeselValidator.isValidFormat("123abc"));
        assertTrue(PeselValidator.isValidFormat("12345678901"));
    }

    @Test
    void getBirthDate() {

    }
}