//package io.github.donespeak.springbootsamples.jpa;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.RandomUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.util.FileCopyUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.SynchronousQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.IntStream;
//
///**
// * @author DoneSpeak
// */
//public class TempTest {
//
//    @Test
//    public void test() throws URISyntaxException {
//
//        URI uri = new URI("http://home");
//        URI result = uri.resolve("h%20h");
//        System.out.println(result.toASCIIString());
//        System.out.println(result.toString());
//    }
//
//    public static void main() throws URISyntaxException, IOException {
//        ExecutorService es = new ThreadPoolExecutor(0, 4,
//                1L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
//
//        es.submit(() -> {
//            System.out.println("jjjj");
//        });
//        es.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("hahah");
//        });
//
//        es.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(4);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("keke");
//        });
//
//        System.out.println("---- end ---");
//
////        IntStream.range(1, 100).forEach(i -> System.out.println(generateRandomKey(" ")));
//    }
//
//    private final static int defaultKeyLength = 10;
//
//    public static String generateRandomKey(String containAtLeastOne) {
//        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
//
//        int atLeastLen = RandomUtils.nextInt(1, defaultKeyLength + 1);
//        int alphnLen = defaultKeyLength - atLeastLen;
//
//        StringBuilder result = new StringBuilder();
//        while(true) {
//            boolean chooseAtLeast = alphnLen == 0 || (atLeastLen > 0 && RandomUtils.nextBoolean());
//
//            if(chooseAtLeast) {
//                int index = RandomUtils.nextInt(0, containAtLeastOne.length());
//                result.append(containAtLeastOne.charAt(index));
//                atLeastLen --;
//            } else if (alphnLen > 0) {
//                int index = RandomUtils.nextInt(0, alphanumeric.length());
//                result.append(alphanumeric.charAt(index));
//                alphnLen --;
//            }
//            if (atLeastLen == 0 && alphnLen == 0) {
//                break;
//            }
//        }
//
//        return result.toString();
//    }
//
//    public static String generateRandomKeyWithIllegalUriChars() {
//        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
//        String illegalUriChars = " %<>[]{}";
//
//        int illegalLen = RandomUtils.nextInt(1, defaultKeyLength + 1);
//        int alphnLen = defaultKeyLength - illegalLen;
//
//        StringBuilder result = new StringBuilder();
//        while(true) {
//            boolean chooseIllegal = alphnLen == 0 || (illegalLen > 0 && RandomUtils.nextBoolean());
//            if(chooseIllegal) {
//                int index = RandomUtils.nextInt(0, illegalUriChars.length());
//                result.append(illegalUriChars.charAt(index));
//                illegalLen --;
//            } else if (alphnLen > 0) {
//                int index = RandomUtils.nextInt(0, alphanumeric.length());
//                result.append(alphanumeric.charAt(index));
//                alphnLen --;
//            }
//            if (illegalLen == 0 && alphnLen == 0) {
//                break;
//            }
//        }
//
//        return result.toString();
//    }
//
//    public static void url() throws URISyntaxException, IOException {
//        System.out.println(encode("<>"));
//        System.out.println(decode("%2F%20+"));
//        URI context = new URI("file:///Users/guanrongyang/Desktop/");
//
//        String[] paths = "+ /?%#&=<>?][{}-*$@".split("");
//        System.out.println(String.join("", paths));
//        for (String p: paths) {
//            String newP = "";
//            try {
//                URI uri = context.resolve(p);
//                System.out.println(uri);
//            } catch (Exception e) {
//                newP = URLEncoder.encode(p, "utf-8");
//                System.err.println("p: " + p + ", new p: " + newP + "," + e.getMessage());
//            }
//            System.out.println("================");
//        }
//        System.out.println(createIllegalUriPath());
//
//        String filePath = "/Users/guanrongyang/Desktop/" + URLEncoder.encode("/Users/guanrongyang/Desktop/uri/fangao.txt", "utf-8");
//        File file = new File(filePath);
//        FileCopyUtils.copy("haha".getBytes(), file);
//        System.out.println(filePath + ": " + file.exists());
//
//        String subPath = encode("project/40591/report/Full-scope Underwriting Report好孩子160880[>_<]%%%}{3647419.txt");
//
//        URI result = context.resolve(subPath);
//        System.out.println("result.path: " + result.getPath());
//        Path path = Paths.get(result.getSchemeSpecificPart());
//
//        File fileOfPath = path.toFile();
//
//        if (!fileOfPath.getParentFile().exists()) {
//            fileOfPath.getParentFile().mkdirs();
//        }
//        FileCopyUtils.copy("haha".getBytes(), fileOfPath);
//
//        System.out.println(fileOfPath.getParent());
//        System.out.println(fileOfPath.getName());
//
//        System.out.println(result);
//    }
//
//    public static String createIllegalUriPath() {
//        return RandomStringUtils.random(4, "123456789");
//    }
//
//    private static String encode(String val) throws UnsupportedEncodingException {
//        String encodedKey = URLEncoder.encode(val, "utf-8");
//        return encodedKey.replaceAll("\\+", "%20");
//    }
//
//    private static String decode(String val) throws UnsupportedEncodingException {
//        return URLDecoder.decode(val, "utf-8");
//    }
//}
