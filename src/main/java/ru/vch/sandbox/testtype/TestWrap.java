package ru.vch.sandbox.testtype;

import java.time.LocalDate;

public class TestWrap {


    public static void main(String[] args) {


        ParamWrap paramWrap = new ParamWrap(0, 0L, "0", LocalDate.now());

        DemoType demoType = new DemoType();
        demoType.demoType(paramWrap.getP1(), paramWrap.getP2(), paramWrap.getP3(), paramWrap.getP4());

        System.out.println("Результат");
        System.out.println(paramWrap.getP1());
        System.out.println(paramWrap.getP2());
        System.out.println(paramWrap.getP3());
        System.out.println(paramWrap.getP4());
        System.out.println("/////////");
    }
}
