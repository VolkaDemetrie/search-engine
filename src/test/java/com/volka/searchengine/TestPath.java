package com.volka.searchengine;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPath {

    @Test
    public void test() {
        Path base = Paths.get("/home/volka").resolve("asdf").resolve("something").resolve("anananan");

        System.out.println(base);
    }

    /**
     * duration :: 3.5219649
     * free Memory :: 25988624
     */
    @Test
    public void performanceBuilderTest() {

        long startTime = System.nanoTime();

        long startFreeMemory = Runtime.getRuntime().freeMemory();

        StringBuilder sb = null;
        Path path = null;
        for (int i = 0; i < 10000000; i++) {
            sb = new StringBuilder();
            sb.append("/home/volka");
            sb.append(File.separator);
            sb.append("asdf");
            sb.append(File.separator);
            sb.append("something");
            sb.append(File.separator);
            sb.append("anananan");
            path = Paths.get(sb.toString());
        }

        long endFreeMemory = Runtime.getRuntime().freeMemory();
        long endTime = System.nanoTime();

        System.out.println("duration :: " + (double) (endTime - startTime) / 1000000000 + "\nfree Memory :: " + (startFreeMemory - endFreeMemory));
    }


    /**
     * duration :: 4.9437626
     * free Memory :: 51213872
     */
    @Test
    public void performancePathTest() {
        long startTime = System.nanoTime();
        long startFreeMemory = Runtime.getRuntime().freeMemory();

        Path path = null;
        for (int i = 0; i < 10000000; i++) {
            path = Paths.get("/home/volka").resolve("asdf").resolve("something").resolve("anananan");
        }
        long endFreeMemory = Runtime.getRuntime().freeMemory();
        long endTime = System.nanoTime();

        System.out.println("duration :: " + (double) (endTime - startTime) / 1000000000 + "\nfree Memory :: " + (startFreeMemory - endFreeMemory));
    }

    /**
     * duration :: 0.9054591
     * free Memory :: 75527728
     */
    @Test
    public void performancePathTest2() {
        Path path1 = Paths.get("/home/volka");
        Path path2 = Paths.get("asdf");
        Path path3 = Paths.get("something");
        Path path4 = Paths.get("anananan");

        long startTime = System.nanoTime();
        long startFreeMemory = Runtime.getRuntime().freeMemory();

        Path path = null;
        for (int i = 0; i < 10000000; i++) {
            path = path1.resolve(path2).resolve(path3).resolve(path4);
        }
        long endFreeMemory = Runtime.getRuntime().freeMemory();
        long endTime = System.nanoTime();

        System.out.println("duration :: " + (double) (endTime - startTime) / 1000000000 + "\nfree Memory :: " + (startFreeMemory - endFreeMemory));
    }

    /**
     * duration :: 2.667678
     * free Memory :: 147129904
     */
    @Test
    public void performancePathTest2Mix() {
        Path path1 = Paths.get("/home/volka");
        Path path2 = Paths.get("asdf");

        long startTime = System.nanoTime();
        long startFreeMemory = Runtime.getRuntime().freeMemory();

        Path path = null;
        for (int i = 0; i < 10000000; i++) {
            path = path1.resolve(path2).resolve("something").resolve("anananan");
        }
        long endFreeMemory = Runtime.getRuntime().freeMemory();
        long endTime = System.nanoTime();

        System.out.println("duration :: " + (double) (endTime - startTime) / 1000000000 + "\nfree Memory :: " + (startFreeMemory - endFreeMemory));
    }
}
