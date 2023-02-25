import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Beak2974_1 {
    private static class selfProduct{
        long i;
        long selfProductNum;

        public selfProduct(long i) {
            this.i = i;
            long tmp = i;
            selfProductNum = i;
            while (tmp/10 != 0){
                selfProductNum *= (tmp%10);
                tmp /= 10;
            }
            selfProductNum *= (tmp%10);
        }

        @Override
        public String toString() {
            return "{i=" + i +
                    ", Num=" + selfProductNum +
                    "}\n";
        }
    }
    private static void mutiCombination(long combi, long mutiplyAll, int length){
//        if (A < mutiplyAll && combi > A)
//        if (mutiplyAll * combi >= A)
        count(combi,mutiplyAll,length);
        for (int i = (int)(combi%10); i < 10; i++) {
            if((mutiplyAll * i) * (combi*10 + i) <= B)
                mutiCombination(combi*10 + i, mutiplyAll * i, length+1);
        }
    }
    private static boolean[] visited;
    private static int[] ints, arr;
    private static long arrToLong(int[] toLong){
        long ans = 0;
        long tentimes = 1;
        for (int i = 0; i < toLong.length; i++) {
            ans += toLong[i]*(tentimes);
            tentimes*=10;
        }
        return ans;
    }
    private static void countBetweenMinMax(long min, long max, int goalLength,int[] combis, int num){
        if (num == 0){ //setting
            visited = new boolean[goalLength];
            ints = combis;
            arr = new int[goalLength];
        }
        if (num == goalLength){
            long number = arrToLong(arr);
            if (min < number && number <= max){
                countAll++;
                System.out.println(number);
            }
            return;
        }
        int value = -1;
        for (int i = 0; i < goalLength; i++) {
            if (!visited[i] && (value != ints[i])){
                value = ints[i];
                visited[i] = true;
                arr[num] = ints[i];
                countBetweenMinMax(min,max,goalLength,combis,num+1);
                visited[i] = false;
            }
        }
    }
    private static void count(long combi, long mutiplyAll, int length){
        long min = A/mutiplyAll;
        long max = B/mutiplyAll;
        int minLength = Long.toString(min).length();
        int maxLength = Long.toString(max).length();
        if (minLength == maxLength){
            int[] combis = new int[minLength];
            int i = 0;
            for (; i < minLength-length; i++) {
                combis[i] = 1;
            }
            int[] combiarr = Long.toString(combi).chars().map(n -> n - '0').toArray();
            int combiattidx = 0;
            for (; i < minLength; i++) {
                combis[i] = combiarr[combiattidx++];
            }
            countBetweenMinMax(min,max,minLength,combis,0);

        }
        else{
            for (int i = minLength+1; i < maxLength; i++) {
                if (length < i){
                    countAll += getRepitionCombination(i-length+1,i-length);
                }
            }
            {
                int[] combis = new int[minLength];
                int i = 0;
                for (; i < minLength-length; i++) {
                    combis[i] = 1;
                }
                int[] combiarr = Long.toString(combi).chars().map(n -> n - 'a').toArray();
                int combiattidx = 0;
                for (; i < minLength; i++) {
                    combis[i] = combiarr[combiattidx++];
                }
                countBetweenMinMax(min,(1<<(minLength+1))-1,minLength,combis,0);
            }
            {
                int[] combis = new int[maxLength];
                int i = 0;
                for (; i < maxLength-length; i++) {
                    combis[i] = 1;
                }
                int[] combiarr = Long.toString(combi).chars().map(n -> n - 'a').toArray();
                int combiattidx = 0;
                for (; i < minLength; i++) {
                    combis[i] = combiarr[combiattidx++];
                }
                countBetweenMinMax((1<<(maxLength-1)),max,minLength,combis,0);
            }

        }

        if (A%mutiplyAll == 0)
            min--;
        int ans;
//        if (min < ans && ans <=max)
    }
    private static long getRepitionCombination(long n, long r){
        return getCombination(n+r-1,r);
    }
    private static long getCombination(long n, long r){
        r = Math.min(r, n-r);
        long answer = 1l;
        for (int i = 0; i < r; i++) {
            answer *= n--;
        }
        for (int i = 1; i <= r; i++) {
            answer /= i;
        }
        return answer;
    }

    //    private static void mutiCombination(long combi, int count){
//        long mutiplyAll = 1;
//        long tmp = combi;
//        while (tmp/10 != 0){
//            mutiplyAll *= tmp%10;
//            tmp /= 10;
//        }
//        mutiplyAll *= tmp%10;
////        if (A < mutiplyAll && combi > A)
//            System.out.println("combi = " + combi + ", multiply = " + mutiplyAll);
//        for (int i = (int)combi%10; i < 10; i++) {
//            if(mutiplyAll * i < B && combi*10 + i < B)
//                mutiCombination(combi*10 + i, count + 1);
//        }
//    }
    private static long A,B,countAll = 0l;
    private static int Alen,Blen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        Alen = s.length();
        A = Long.parseLong(s);
        s = st.nextToken();
        Blen = s.length();
        B = Long.parseLong(s);




        ArrayList<selfProduct> test = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            test.add(new selfProduct(i));
        }
        test.sort((o1, o2) -> Long.compare(o1.selfProductNum,o2.selfProductNum));
        long count = 0;
        for (selfProduct selfProduct : test) {
            if (A <= selfProduct.selfProductNum && selfProduct.selfProductNum <= B){
                count++;
                System.out.println(selfProduct);
            }
        }
        System.out.println(count);
        System.out.println(test);


//        for (int i = 0; i < 101; i++) {
//            long tmp = i;
//            long ans = tmp;
//            while (tmp/10 != 0){
//                ans *= (tmp%10);
//                tmp /= 10;
//            }
//            ans *= (tmp%10);
//            System.out.println(ans);
//        }
//        long tmp = 2612l;
//        long ans = tmp;
//        while (tmp/10 != 0){
//            ans *= (tmp%10);
//            tmp /= 10;
//        }
//        ans *= (tmp%10);
//        System.out.println(ans);
    }
}
