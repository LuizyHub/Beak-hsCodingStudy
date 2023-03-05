import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak17387 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x1 -= Integer.parseInt(st.nextToken());
        y1 -= Integer.parseInt(st.nextToken());
        x2 -= Integer.parseInt(st.nextToken());
        y2 -= Integer.parseInt(st.nextToken());
        System.out.println((x1*x2 + y1*y2) > 0 ? 0 : 1);
    }
}
// 세점 평행할때 다시생각
// 시점끼리 종점끼리 벡터가 방향 반대일때 오케이