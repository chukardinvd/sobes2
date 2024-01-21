package ru.vch.sandbox.testtype;

import java.time.LocalDate;

public class Test {


    public static void main(String[] args) {

        DemoType demoType = new DemoType();
        demoType = new DemoType();

        int p1 = 0;
        Long p2 = 1024L;
        String p3 = "0";
        LocalDate p4 = LocalDate.now();

        demoType.demoType(p1, p2, p3, p4);

        System.out.println("Результат");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println("/////////");
    }
}
