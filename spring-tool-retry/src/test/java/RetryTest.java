import org.junit.jupiter.api.Test;
import org.springframework.classify.Classifier;
import org.springframework.classify.SubclassClassifier;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DoneSpeak
 */
public class RetryTest {

    @Test
    public void test1() throws Exception {
        System.out.println("执行之前");
        RetryTemplate template = new RetryTemplate();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        // Set the max retry attempts
        policy.setMaxAttempts(5);
        template.setRetryPolicy(policy);

        try {
            template.execute(retryContext -> {
                System.out.println("Processing...");
                // 该异常用户触发重试
                throw new RuntimeException("this is an error");
            }, context -> {
                System.out.println("最后一次执行");
                if (context.getLastThrowable() != null) {
                    context.getLastThrowable().printStackTrace();
                    System.out.println("最后的异常：" + context.getLastThrowable().getMessage());
                }
                return false;
            });
        } catch (Exception e) {
            System.out.println("this is : " + e);
        }
        try {
            template.execute(retryContext -> {
                System.out.println("Processing..." + retryContext.getRetryCount() + ": " + retryContext.getLastThrowable());
                // 该异常用户触发重试
                throw new IllegalStateException("this is an error");
            });
        } catch (Exception e) {
            System.out.println("catch a : " + e);
        }
        System.out.println("执行之后");
    }

    public static void testSeccessful() throws Exception {
        System.out.println("执行之前");
        RetryTemplate template = new RetryTemplate();
        template.execute(retryContext -> {
            System.out.println("Processing...");
            return true;
        }, context -> {
            System.out.println("最后一次执行");
            if(context.getLastThrowable() != null) {
                context.getLastThrowable().printStackTrace();
                System.out.println("最后的异常：" + context.getLastThrowable().getMessage());
            }
            return false;
        });
        System.out.println("执行之后");
    }

    public static class AxException extends InterruptedException {
        public AxException() {}
    }
//
//    public static void main(String[] args) throws Exception {
//        System.out.println(Math.pow(10, 1.0/3));
//        testSeccessful();
//        System.out.println("继续执行");
//
//        Map<Class<? extends Throwable>, String> map = new HashMap<>();
//        for(Class<? extends Throwable> c: Arrays.asList(Exception.class, InterruptedException.class, RuntimeException.class)) {
//            map.put(c, c.getSimpleName());
//        }
//        Classifier<Throwable, String> classifier = new SubclassClassifier<>(map, "EMPTY");
//        String result = classifier.classify(new Error());
//        System.out.println("result: " + result);
//    }
}
