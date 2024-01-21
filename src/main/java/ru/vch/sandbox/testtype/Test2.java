package ru.vch.sandbox.testtype;

public class Test2 {


    public static void main(String[] args) {

        int i1 = 128;
        int i1_1 = 127+1;

        Integer I2 = 2;
        Integer I2_1 = 1+1;

        Integer I3 = 128;
        Integer I3_1 = 127+1;


        String S4 = I2.toString();
        String S4_1 = I2_1.toString();


        System.out.println("///////");
        System.out.println(i1 == i1_1);
        System.out.println(I2 == I2_1);
        System.out.println(I3 == I3_1);

        System.out.println(S4==S4_1);
        System.out.println(S4.equals(S4_1));

        System.out.println("///////");


    }
}
