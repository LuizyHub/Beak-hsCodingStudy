import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Beak1208 {
    private static int N, S, count = 0;
    private static int[] arr;

    private static void solution(int idx, int target){
        for (int i = idx - 1; i >= 0; i--) {
            int periodSum = arr[idx] - arr[i];
            System.out.println(target + " " + idx + " " + i);
            System.out.println(periodSum - target + S);
            if (periodSum == target)
                count++;
            solution(i - 1, target - periodSum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        solution(N,S);

//        System.out.print(count);
    }
}

/*
5 0
0 1 2 4 8

 */

/*
0 5 4
8 5
-8 3 2
10 53
-10 1 0
10 531
-8 3 1
11 532
-8 3 0
11 5321
0 5 3
12 54
-12 2 1
13 542
-12 2 0
13 5421
0 5 2
14 543
-14 1 0
14 5431
0 5 1
15 5432
0 5 0
15 54321
 */