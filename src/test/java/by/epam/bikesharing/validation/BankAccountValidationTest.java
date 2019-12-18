package by.epam.bikesharing.validation;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountValidationTest {

    @Test
    void isValidCvv() {
        assertTrue(BankAccountValidation.isValidCvv("666"));
        assertFalse(BankAccountValidation.isValidCvv("00O"));
    }
}