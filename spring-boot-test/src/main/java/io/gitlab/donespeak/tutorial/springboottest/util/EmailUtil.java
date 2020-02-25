package io.gitlab.donespeak.tutorial.springboottest.util;

/**
 * @author Yang Guanrong
 * @date 2020/02/24 22:10
 */
public class EmailUtil {

    public static String getNameFromEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }

    public static boolean isMail(String email) {
        return email.contains("@");
    }
}
