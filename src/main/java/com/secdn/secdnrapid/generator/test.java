package com.secdn.secdnrapid.generator;

import java.io.File;

/**
 * @author secdn
 * @create 2018-12-06
 */
public class test {

    private static File file = new File("");
    private static String path = file.getAbsolutePath();

    public static void main(String[] args) throws InterruptedException {
        System.out.print(path);
    }

}
