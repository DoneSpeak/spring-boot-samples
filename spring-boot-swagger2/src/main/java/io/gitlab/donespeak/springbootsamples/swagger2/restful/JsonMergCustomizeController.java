package io.gitlab.donespeak.springbootsamples.swagger2.restful;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DoneSpeak
 */
public class JsonMergCustomizeController {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add(1, "c");
        list.add(2, "d");
        System.out.println(list);
        list.add(list.size(), "dd");
        System.out.println(list);
    }
}
