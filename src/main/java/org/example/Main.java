package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println((int)'-');
        int i = 2143;
        System.out.println((int) Math.ceil(Math.log(i) / Math.log(2)));
        System.out.println(Integer.toBinaryString(i).length());

        System.out.println(Integer.toBinaryString(-i));
        System.out.println(Integer.toBinaryString(i));

        System.out.println(i & ~(i & (-i)));

        System.out.println((int)Math.pow(2,10));
        System.out.println(1<<10);


    }
}