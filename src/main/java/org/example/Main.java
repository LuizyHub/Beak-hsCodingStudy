package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int cnt = 0;
        switch (n % 10){
            case 5: case 0:
                cnt = n/5;
                break;
            case 2 : case 7:
                if(n<12)
                    cnt = -1;
                else
                    cnt = (n-12)/5 + 4;
                break;
            case 3: case 8:
                if (n<3)
                    cnt = -1;
                else
                    cnt = (n-3)/5 + 1;
                break;
            case 6: case 1:
                if (n<6)
                    cnt = -1;
                else
                    cnt = (n-6)/5 + 2;
                break;
            case 9: case 4:
                if (n<9)
                    cnt = -1;
                else
                    cnt = (n-9)/5 + 3;
                break;
        }
        System.out.println(cnt);

    }
}
