import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beak1197 {
    private static class Node implements Comparable<Node>{
        public int to;
        public int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node)obj;
            if (this.weight == node.weight && this.to == node.to)
                return true;
            else
                return false;
        }

        @Override
        public int compareTo(Node o) {
            if (this.weight == o.weight)
                return Integer.compare(this.to,o.to);
            else
                return Integer.compare(this.weight,o.weight);
        }
    }
    private static ArrayList<Node>[] weights;
    private static int[] distance;
    private static boolean[] visit;
    private static int V,E;
    private static int primBST(int start){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // weight, index
        TreeSet<Node> treeSet = new TreeSet<>(); // Node(to, weight)
        int total = 0;
        distance[start] = 0;
        do {  // O(N)
            visit[start] = true;
            total += distance[start];
            for (int i = 0; i < weights[start].size(); i++) {
                Node node = weights[start].get(i);
                if (!visit[node.to] && distance[node.to] > node.weight){
                    if (distance[node.to] == Integer.MAX_VALUE) { // never was in tree;
                        distance[node.to] = node.weight;
                        treeSet.add(new Node(node.to, node.weight));
                    }
                    else { // is in tree;
                        treeSet.remove(new Node(node.to, distance[node.to]));
                        distance[node.to] = node.weight;
                        treeSet.add(new Node(node.to, node.weight));
                    }
                }
            }
            start = treeSet.first().to;
            treeSet.remove(treeSet.first());
        }while (!treeSet.isEmpty());


//        do {  // O(N)
//            visit[start] = true;
//            total += distance[start];
//            for (int i = 0; i < weights[start].size(); i++) {
//                Node node = weights[start].get(i);
//                if (!visit[node.to] && distance[node.to] > node.weight){
//                    if (distance[node.to] == Integer.MAX_VALUE) { // never was in tree;
//                        distance[node.to] = node.weight;
//                        treeMap.put(node.weight, node.to);
//                    }
//                    else { // is in tree;
//                        ArrayList<Integer> removeList = new ArrayList<>(); // list of same distance
//                        while (treeMap.get(distance[node.to]) != node.to){
//                            removeList.add(treeMap.remove(distance[node.to]));
//                        }
//                        treeMap.remove(distance[node.to]);
//                        for (Integer integer : removeList) {
//                            treeMap.put(distance[node.to], integer); // put it back
//                        }
//                        distance[node.to] = node.weight;
//                        treeMap.put(node.weight, node.to);
//                    }
//                }
//            }
//            start = treeMap.remove(treeMap.firstKey());
//        }while (!treeMap.isEmpty());

//        while (!treeMap.isEmpty()) {  // O(N)
//            visit[start] = true;
//            total += distance[start];
//            for (int i = 0; i < weights[start].size(); i++) {
//                Node node = weights[start].get(i);
//                if (!visit[node.to] && distance[node.to] > node.weight){
//                    if (distance[node.to] == Integer.MAX_VALUE) { // never was in tree;
//                        distance[node.to] = node.weight;
//                        treeMap.put(node.weight, node.to);
//                    }
//                    else { // is in tree;
//                        ArrayList<Integer> removeList = new ArrayList<>(); // list of same distance
//                        while (treeMap.get(distance[node.to]) == node.to){
//                            removeList.add(treeMap.remove(distance[node.to]));
//                        }
//                        treeMap.remove(distance[node.to]);
//                        for (Integer integer : removeList) {
//                            treeMap.put(distance[node.to], integer); // put it back
//                        }
//                        distance[node.to] = node.weight;
//                        treeMap.put(node.weight, node.to);
//                    }
//                }
//            }
//            start = treeMap.remove(treeMap.firstKey());
//        }
        total += distance[start];
        return total;
    }
    private static int prim(int start){
        int total = 0;
        int minDistance = 0;
        for (int i = 0; i < V; i++) {
            total += minDistance;
            visit[start] = true;
            minDistance = Integer.MAX_VALUE;
            int next = -1;
            for (int j = 0; j < weights[start].size(); j++) {
                Node node = weights[start].get(j);
                if (!visit[node.to]){
                    if (distance[node.to] > node.weight)
                        distance[node.to] = node.weight;
                    if (distance[node.to] <= minDistance) {
                        minDistance = distance[node.to];
                        next = node.to;
                    }
                }
            }
            start = next;
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
//        V = Integer.parseInt(br.readLine());
//        E = Integer.parseInt(br.readLine());


        distance = new int[V];
        Arrays.fill(distance,Integer.MAX_VALUE);
        visit = new boolean[V];
        weights = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            weights[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            weights[from].add(new Node(to,weight));
            weights[to].add(new Node(from,weight));
        }

        System.out.println(primBST(0));
    }
}
