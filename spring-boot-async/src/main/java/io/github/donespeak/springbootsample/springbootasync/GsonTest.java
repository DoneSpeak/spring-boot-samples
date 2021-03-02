package io.github.donespeak.springbootsample.springbootasync;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author DoneSpeak
 */
public class GsonTest {

    public static class Name implements Serializable{
        private String name = Only.value;

        public static String DEFAULT_NAME = "DEFAULT";
        public static class Only {
            public static String value = "D_VALUE";
        }
    }

    public static class Temp extends Name {
        final static Random RANDOM = new Random();
        private Temp tmp = null;
        private String name = "";
        private int age = 1;
        private Class<? extends Throwable> cause = RuntimeException.class;

        @Override
        public String toString() {
            return "name:" + name + ", age:" + age + ",random:" + RANDOM.nextInt();
        }

        public void setValue(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static class JsonObjWrap {
        private JsonObject jsonObject;

        public void setJsonObject(JsonObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        public JsonObject getJsonObject() {
            return jsonObject;
        }
    }

    public static void toJson() {
        Temp tmp = new Temp();
        tmp.setValue("haha", 12);
        System.out.println(tmp);
        Gson gson = new Gson();
        String json = gson.toJson(tmp);
        System.out.println(json);
        Temp tm = gson.fromJson(json, Temp.class);
        System.out.println(tm);
    }

    public static void serialize() throws IOException, ClassNotFoundException {
        byte[] bytes = null;
        String theName = "xxx";
        try(ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
            Name name = new Temp() {

                @Override
                public String toString() {
                    return theName + "-" + System.currentTimeMillis();
                }
            };
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", theName);
            out.writeObject(jsonObject);
            // out.writeObject(new Date());

            // Supplier<String> stringSupplier = () -> "";
            // out.writeObject(stringSupplier);

            bytes = byteOut.toByteArray();
            System.out.println(new String(bytes));
        }

        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            Temp temp = (Temp) input.readObject();
            // Date temp = (Date) input.readObject();
            System.out.println(temp);
        }
    }

    @Data
    public static class CompositeObj {
        // private ZoneId zoneId = ZoneId.systemDefault();
        private LocalDateTime data = LocalDateTime.now();
        // private Iterable<Class<? extends Exception>> exceptions = Arrays.asList(RuntimeException.class);
        private Duration duration = Duration.ofSeconds(3);
        private List<Class<? extends Exception>> exceptionList = Arrays.asList(RuntimeException.class);
    }

    public static void main(String[] args) throws Exception {
        serialize();
        // JsonObjWrap js = new JsonObjWrap();
        // JsonObject jsonObject = new JsonObject();
        // jsonObject.addProperty("haha", "goood");
        // js.setJsonObject(jsonObject);
        //
        // Gson gson = new Gson();
        // String json = gson.toJson(js);
        // System.out.println(json);
        //
        // System.out.println("=============");
        // CompositeObj compositeObj = new CompositeObj();
        // System.out.println(compositeObj);
        // String js2 = gson.toJson(compositeObj);
        // System.out.println(js2);
        // compositeObj = gson.fromJson(js2, CompositeObj.class);
        // System.out.println(compositeObj);
    }
}
