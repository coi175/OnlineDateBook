package model;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class PasswordSecurityServiceTest {
    @Test
    public void validatePassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertEquals(true, PasswordSecurityService.validatePassword("password1sdfsd",
                "1000:16e0e24435c211fb07d7b8382b131556:e5e5867ec6ea13fd3cabd452e86ffad61c3d0a321aa8b5f7c29402521dfc6fd77f5fee7445b0887944755e142f993a5686cd70fbc5fcd8854ba0c359cdabefa2"));
        assertEquals(false, PasswordSecurityService.validatePassword("1353g35f4",
                "1000:16e0e24435c211fb07d7b8382b131556:e5e5867ec6ea13fd3cabd452e86ffad61c3d0a321aa8b5f7c29402521dfc6fd77f5fee7445b0887944755e142f993a5686cd70fbc5fcd8854ba0c359cdabefa2"));
    }
}