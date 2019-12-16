package by.epam.bikesharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkPassword() {
        PasswordHash hash = new PasswordHash("password");
    }

    @Test
    void getHash() {
        PasswordHash hash = new PasswordHash("password");
        PasswordHash password = new PasswordHash(hash.getHash(), hash.getSalt());
        assertTrue(password.isCorrectPassword("password"));
    }

    @Test
    void getSalt() {
    }
}