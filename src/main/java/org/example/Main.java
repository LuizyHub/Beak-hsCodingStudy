package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static class Node {
        int n;
        int cnt;

        public StringBuilder sb;

        public Node(int n, int cnt, StringBuilder sb) {
            this.n = n;
            this.cnt = cnt;
            this.sb = sb;
        }
    }


    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        boolean[] check = new boolean[n + 1];
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(n, 0, new StringBuilder(Integer.toString(n))));

        while(!dq.isEmpty()) {
            Node node = dq.pollFirst();

            if (node.n == 1) {
                System.out.print(node.cnt + "\n" + node.sb);
                break;
            }



            int[] calc;
            if (node.n % 2 == 0 && node.n % 3 == 0) {
                calc = new int[]{node.n / 3, node.n / 2, node.n - 1};
            } else if (node.n % 2 == 0) {
                calc = new int[]{node.n / 2, node.n - 1};
            } else if (node.n % 3 == 0) {
                calc = new int[]{node.n / 3, node.n - 1};
            } else {
                calc = new int[]{node.n - 1};
            }

            for (int next: calc) {
                if (next < 1) continue;
                if (check[next]) continue;
                check[next] = true;

//                dq.addLast(new Node(next, node.cnt + 1));
            }
        }

    }
}