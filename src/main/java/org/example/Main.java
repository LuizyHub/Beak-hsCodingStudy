package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        System.out.println("\\    /\\");
        System.out.println(" )  ( \')");
        System.out.println("(  /  )");
        System.out.println(" \\(__)|");
    }


    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] nums = new int[n + 1][];
        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nums[i] = new int[i + 1];
            for (int j = 1; j <= i; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                nums[i][j] = Math.max(nums[i - 1][j - 1] + nums[i][j], nums[i - 1][j] + nums[i][j]);
            }
        }

        System.out.println(Arrays.toString(nums[n]));
    }
}