import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak17387 {
//    private static int gcd(int p, int q)
//    {
//        if (q == 0) return p;
//        return gcd(q, p%q);
//    }
    private static class Dot{
        public long x,y;

        public Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }

//        @Override
//        public String toString() {
//            final StringBuilder sb = new StringBuilder("Dot{");
//            sb.append("x=").append(x);
//            sb.append(", y=").append(y);
//            sb.append('}');
//            return sb.toString();
//        }
    }
//    private static boolean isParallel
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Dot[] dots = new Dot[4];
        st = new StringTokenizer(br.readLine());
        dots[0] = new Dot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        dots[1] = new Dot(Integer.parseInt(st.nextToken()) - dots[0].x,Integer.parseInt(st.nextToken()) - dots[0].y);
        int xsign = 1,ysign = 1;
        if (dots[1].x < 0){
            xsign = -1;
            dots[1].x *= xsign;
        }
        if (dots[1].y < 0){
            ysign = -1;
            dots[1].y *= ysign;
        }
        st = new StringTokenizer(br.readLine());
        dots[2] = new Dot(xsign*(Integer.parseInt(st.nextToken()) - dots[0].x),ysign*(Integer.parseInt(st.nextToken()) - dots[0].y));
        dots[3] = new Dot(xsign*(Integer.parseInt(st.nextToken()) - dots[0].x),ysign*(Integer.parseInt(st.nextToken()) - dots[0].y));
        dots[0] = new Dot(0,0);
        //        System.out.println((x1*x2 + y1*y2) > 0 ? 0 : 1);
//        System.out.println(Arrays.toString(dots));
        int ans = 0;
        long rel1 = dots[2].y*dots[1].x - dots[1].y*dots[2].x;
        long rel0 = dots[3].y*dots[1].x - dots[1].y*dots[3].x;
        if (!((rel0<0 && rel1<0) || (rel0>0 && rel1>0))){
            rel1 = (dots[1].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[1].y - dots[2].y)*(dots[3].x - dots[2].x); // 4000000*4000000*2 = 32,000,000,000,000
            rel0 = (dots[0].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[0].y - dots[2].y)*(dots[3].x - dots[2].x);
            if (!((rel0<0 && rel1<0) || (rel0>0 && rel1>0)))
                ans = 1;
        }
//        if ((dots[2].y*dots[1].x - dots[1].y*dots[2].x)*(dots[3].y*dots[1].x - dots[1].y*dots[3].x) <= 0){
//            rel1 = (dots[1].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[1].y - dots[2].y)*(dots[3].x - dots[2].x); // 4000000*4000000*2 = 32,000,000,000,000
//            rel0 = (dots[0].x-dots[2].x)*(dots[3].y-dots[2].y) - (dots[0].y - dots[2].y)*(dots[3].x - dots[2].x);
//            if (rel0*rel1 <= 0)
//                ans = 1;
//
//////            long rel2 = dots[2].y*dots[1].y + dots[1].x*dots[2].x;
////            long rel2 = dots[1].y*dots[2].y + dots[2].x*dots[1].x;
//////            System.out.println(rel2);
////            if (0 <= rel2){
//////                long rel3 = dots[3].y*dots[1].y + dots[1].x*dots[3].x;
////                long rel3 = dots[1].y*dots[3].y + dots[3].x*dots[1].x;
//////                System.out.println(rel3);
////                if (0 <= rel3)
////                    ans = 1;
////            }
//
//        }
        System.out.println(ans);
    }
}
