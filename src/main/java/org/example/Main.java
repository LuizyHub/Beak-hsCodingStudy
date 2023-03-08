package org.example;

public class Main{
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(511));
        int a = 0b0;
        a = a | 1<<9;
        a = a | 1<<7;

        a = a & 1<<3;
        System.out.println(Integer.bitCount(a));
        System.out.println(Integer.toBinaryString(a));
    }
}