package io.github.donespeak.springbootsample.xxljob;

import java.io.File;
import java.util.Comparator;

/**
 * @author DoneSpeak
 */
public class TestMain {

    public static void main(String[] args) {
        File file = new File("/Users/yangguanrong/Desktop/1007070_1_Texas");

        for(File f: file.listFiles()) {
            System.out.println("\"" + f.getName() + "\",");
        }
    }
}
