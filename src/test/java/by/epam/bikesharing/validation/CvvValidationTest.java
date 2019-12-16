package by.epam.bikesharing.validation;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CvvValidationTest {

    @Test
    void isValid() {
        assertTrue(CvvValidation.isValid("666"));
        assertFalse(CvvValidation.isValid("00O"));
    }
}