package io.gitlab.donespeak.tutorial.springboottest.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailUtilTest {

    @Test
    public void getNameFromEmail() {
        String email = "xiaoming@163.com";
        String nameExpected = "xiaoming";
        String name = EmailUtil.getNameFromEmail(email);
        assertEquals(nameExpected, name);
    }

    @Test
    public void isMail() {
        String email = "xiaoming@163.com";
        boolean result = EmailUtil.isMail(email);
        assertTrue(result);
    }

    @Test
    public void isMail_false() {
        String email = "xiaoming";
        boolean result = EmailUtil.isMail(email);
        assertFalse(result);
    }
}