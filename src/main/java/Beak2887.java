import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Beak2887 {
    private static int N;
    private static int[][] planet;
    private static int[] distance;
    private static boolean[] visit;
    private static int getWeight(int a, int b){
        return Math.min(Math.abs(planet[a][0] - planet[b][0]) , Math.min(Math.abs(planet[a][1] - planet[b][1]), Math.abs(planet[a][2] - planet[b][2])));
    }
    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
//    private static int prim(int start){ // start = 0
//        int total = 0;
//        for (int i = 0; i < N; i++) {
//            distance[i] = getWeight(start,i);
//        }
//        for (int i = 0; i < N; i++) {
//            int now = -1;
//            int minDistance = Integer.MAX_VALUE;
//            for (int j = 0; j < N; j++) {
//               if (!visit[i] && distance[i] < minDistance){
//                   minDistance = distance[i];
//                   now = j;
//               }
//            }
//            total += minDistance;
//            visit[now] = true;
//            for (int j = 0; j < N; j++) {
//                if (!visit[j]){
//                    int weight = getWeight(now,j);
//                    if (distance[j] > weight)
//                        distance[j] = weight;
//                }
//            }
//        }
//        return total;
//    } //O(n^2)

    private static int prim(int start) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // weight, index
        int total = 0;

        for (int i = 0; i < N; i++) {
            distance[i] = getWeight(start, i);
            if (distance[i] == 0)
                visit[i] = true;
            else
                treeMap.put(distance[i], i);
        }
        visit[start] = true;

        while (!treeMap.isEmpty()) {  // O(N)
            int idx = treeMap.remove(treeMap.firstKey());
            visit[idx] = true;
            total += distance[idx];
            for (int i = 0; i < N; i++) { // O(N)
                if (!visit[i]) {
                    int weight = getWeight(idx, i);
                    if (distance[i] > weight) {
                        treeMap.remove(distance[i]); // O(logN)
                        distance[i] = weight;
                        treeMap.put(weight, i);
                    }
                }
            }
        }
        return total;
    }

////        PriorityQueue<Edge> pq = new PriorityQueue<>();
////        pq.offer(new Edge(start,0));
////        for (int i = 0; i < N; i++) {
////            if (i == start)
////                continue;
////            distance[i] = getWeight(start,i);
////            pq.offer(new Edge(i,distance[i]));
////        }
////        while (!pq.isEmpty()){
////            Edge edge = pq.poll();
////            if (visit[edge.to])
////                continue;
////            visit[edge.to] = true;
////            total += edge.weight;
////
////            for (int i = 0; i < N; i++) {
////                if (!visit[i]){
////                    int weight = getWeight(edge.to,i);
////                    if (distance[i]>weight)
////                        pq.offer(new Edge(i,weight));
//////                    pq.offer(new Edge(i, getWeight(edge.to,i)));
////                }
////            }
////        }
//        return total;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        planet = new int[N][3];
        distance = new int[N];
        visit = new boolean[N];
        Arrays.fill(distance,-1);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            planet[i][0] = Integer.parseInt(st.nextToken());
            planet[i][1] = Integer.parseInt(st.nextToken());
            planet[i][2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(prim(0));
    }
}
