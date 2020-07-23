package io.github.donespeak.springsamples.quartz;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author DoneSpeak
 */
@Data
public class FakeUtil {

    private String a = "aaa";
    private String b = "bbb"
        + "";

    public StrOperator opt() {
        return StrOperator.getInstance((str) -> {
                return true;
            }, (str) -> {
                return "huhu: " + str + ", A: " + a;
            });
    }

    public void change() {
        this.a = a + "|" + a;
    }

    public static interface StrOperator {

        String str(String str);

        public static StrOperator getInstance(final Predicate<String> isLogError,
            final Function<String, String> message) {
            return (str) -> {
                if(isLogError.test(str)) {
                    System.out.println("INNER: " + message.apply(str));
                }
                return message.apply(str);
            };
        }
    }

    public static void main(String[] args) throws Exception {
        Class<? extends Throwable> clazz = RuntimeException.class;
        System.out.println(clazz.isInstance(new IllegalAccessError()));
        System.out.println(clazz.isInstance(new IllegalArgumentException()));
        System.out.println(clazz.isInstance(new Exception()));
    }
}
