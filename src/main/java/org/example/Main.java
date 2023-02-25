package org.example;

public class Main {
    public static void main(String[] args) {
        long num = 9;
        for (int i = 1;num < 1_000_000_000_000_000_000l ; i++) {
            System.out.println(i + " , " + num );
            num *= 9;
        }
    }
}