import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak2467 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int liq[] = new int[N];

        for (int i = 0; i < N; i++) {
            liq[i] = Integer.parseInt(st.nextToken());
        }


        int left =0;
        int right =N-1;
        int Min = liq[left] + liq[right];
        int ans = liq[left] + liq[right];
        int ansLeft = 0, ansRight = 0;
        while (right - left > 1){
            if (Min == 0){
                break;
            }
            else if (Min > 0){
                if (Math.abs(Min) - Math.abs(liq[left] + liq[right-1]) >= 0){
                    right--;
                    Min = liq[left] + liq[right];
                }
                else{
                    if (Math.abs(ans) < Math.abs(Min)){

                    }
                    ans = Math.min(ans,Min);
                    left++;
                    right--;
                    Min = liq[left] + liq[right];
                }
            }
            else if (Min < 0){ // Min < 0
                if (Math.abs(Min) - Math.abs(liq[left+1] + liq[right]) >= 0){
                    left++;
                    Min = liq[left] + liq[right];
                }
                else {
                    ans = Math.min(ans,Min);
                    left++;
                    right--;
                    Min = liq[left] + liq[right];
                }
            }
            else {
                if (Math.abs(Min) - Math.abs(liq[left+1] + liq[right-1]) >= 0){
                    left++;
                    Min = liq[left] + liq[right];
                }
                else {
                    ans = Math.min(ans,Min);
                    break;
                }
            }

        }



        System.out.println(liq[left] + " " + liq[right]);
    }
}
