//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//        new Main().solution();
//    }
//
//    public void solution() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        int[] nums = new int[n];
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < n; i++) {
//            nums[i] = Integer.parseInt(st.nextToken());
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pair {

    public int r;
    public int c;

    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }

}

public class Main {

    public static int n;
    public static int m;
    public ArrayList<Pair> homes;
    public ArrayList<Pair> chickens;
    public int home_cnt;
    public int res = 10000;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void dfs(ArrayList<Pair> candi, int start) {
        if (candi.size() == m) {
            int[] dist = new int[home_cnt];
            Arrays.fill(dist, 100);

            for (Pair chicken : candi) {
                for (int i = 0; i < home_cnt; i++) {
                    dist[i] = Math.min(dist[i], Math.abs(chicken.r - homes.get(i).r) + Math
                            .abs(chicken.c - homes.get(i).c));
                }
            }

            int sum = 0;

            for (int i = 0; i < home_cnt; i++) {
                sum += dist[i];
            }

            res = Math.min(res, sum);

        } else {
            for (int i = start; i < chickens.size(); i++) {
                candi.add(chickens.get(i));
                dfs(candi, i + 1);
                candi.remove(candi.size() - 1);
            }
        }
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        homes = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                int tmp = Integer.parseInt(st.nextToken());

                if (tmp == 1) {
                    homes.add(new Pair(i, j));
                } else if (tmp == 2) {
                    chickens.add(new Pair(i, j));
                }
            }
        }

        home_cnt = homes.size();
        for (int i = 0; i < chickens.size(); i++) {
            ArrayList<Pair> candi = new ArrayList<>();
            candi.add(chickens.get(i));
            dfs(candi, i + 1);
        }

        System.out.println(res);
    }
}