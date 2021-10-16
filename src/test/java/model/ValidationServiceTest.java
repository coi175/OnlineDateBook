package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ValidationServiceTest {

    @Test
    public void validateUsername() {
        // if username is valid
        assertEquals(true, ValidationService.validateUsername("jobbi172"));
        // if username is invalid
        assertEquals(false, ValidationService.validateUsername("fs*2##$"));
    }

    @Test
    public void validateEmail() {
        // if email is valid
        assertEquals(true, ValidationService.validateEmail("somebody@mail.ru"));
        // if email is invalid
        assertEquals(false, ValidationService.validateEmail("uncorrect@mail"));
    }

    @Test
    public void validatePassword() {
        // if password is valid
        assertEquals(true, ValidationService.validatePassword("Fjsi14Fsdfjl1451"));
        // if password is invalid
        assertEquals(false, ValidationService.validatePassword("1234"));
    }

    @Test
    public void validatePasswordMatch() {
        // if passwords matches
        assertEquals(true, ValidationService.validatePasswordMatch("SDf2f2ofjlk2m3f", "SDf2f2ofjlk2m3f"));
        // if passwords don't matches
        assertEquals(false, ValidationService.validatePasswordMatch("12345", "SDf2f2ofjlk2m3f"));
    }
}