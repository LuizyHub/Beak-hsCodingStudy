package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString( ((1<<(5-1)) - 1) << 10 ));
    }
}