package ru.vch.sandbox.testtype;

import java.time.LocalDate;

public class DemoType {

    public void demoType(int p1, Long p2, String p3, LocalDate p4){

        p1+=1;
        p2+=1L;
        p3+="1";
        p4 = p4.plusYears(100);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);

    }

}
