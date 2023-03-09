import java.io.IOException;
import java.util.*;

public class Beak2239 {
    private static int[][] availList = new int[9][9];
    private static int[][] sdoku = new int[9][9];
    private static class Node implements Comparable<Node>, Cloneable{
        public int x,y;
        public int bitCount;

        public Node(int x, int y, int bitCount) {
            this.x = x;
            this.y = y;
            this.bitCount = bitCount;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Node(x,y,bitCount);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y && bitCount == node.bitCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, bitCount);
        }

        //        @Override
//        public boolean equals(Object obj) {
//            Node node = (Node) obj;
//            return (this.x == node.x && this.y == node.y);
//        }

        @Override
        public int compareTo(Node o) {
            int dif = Integer.compare(o.bitCount, this.bitCount);
            if (dif == 0){
                int difx = Integer.compare(this.x, o.x);
                if (difx == 0)
                    return Integer.compare(this.y, o.y);
                return difx;
            }
            return dif;
        }
    }
    private static void renewList(int[][] checkList, int x, int y, int num){
        for (int j = 0; j < 9; j++) {
            checkList[x][j] |= (1<<num);
            checkList[j][y] |= (1<<num);
        }
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                checkList[(x / 3) * 3 + j][(y / 3) * 3 + k] |= (1<<num);
            }
        }
    }
    private static void fill(int[][] checkList, int[][] board, PriorityQueue<Node> empty){
        while (!empty.isEmpty()){
            Node node = empty.poll();
            if (board[node.x][node.y] != 0) continue;
            if (node.bitCount == 8) {
                int bitlist = checkList[node.x][node.y];
                int num = 0;
                for (int i = 1; i <= 9; i++) {
                    if (((bitlist >> i) & 1) == 0) {
                        num = i;
                        break;
                    }
                } // find empty
                renewList(checkList,node.x,node.y,num);
                board[node.x][node.y] = num;
            }
            else {
                empty.add(node);
                ArrayList<Node> changedList = new ArrayList<>();
                for (Node emp : empty) {
                    int bit = Integer.bitCount(checkList[emp.x][emp.y]);
                    if (emp.bitCount != bit)
                        changedList.add(new Node(emp.x,emp.y,bit));

                }
                for (Node emp : changedList) {
                    empty.add(emp);
                }
                if (empty.peek().bitCount != 8){
                    node = empty.poll();
                    for (int i = 1; i <= 9; i++) {
                        if (((node.bitCount >> i) & 1) == 0) {

                            break;
                        }
                    } // find empty

                }
            }

        }
    }

    private static void solve(int[][] checkList, int[][] board){
//        PriorityQueue<Node> empty = new PriorityQueue<>();
        TreeSet<Node> empty = new TreeSet<>();
//        PriorityQueue<Node> zeros = new PriorityQueue<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0){
                    empty.add(new Node(i,j, Integer.bitCount(checkList[i][j])));
                }
            }
        }
        while (!empty.isEmpty()){
            Node node = empty.first();
            if (board[node.x][node.y] != 0) continue;
            if (node.bitCount == 8) {
                int bitlist = checkList[node.x][node.y];
                int num = 0;
                for (int i = 1; i <= 9; i++) {
                    if (((bitlist >> i) & 1) == 0) {
                        num = i;
                        break;
                    }
                } // find empty
                renewList(checkList,node.x,node.y,num);
//                for (int j = 0; j < 9; j++) {
//                    checkList[node.x][j] |= (1<<num);
//                    checkList[j][node.y] |= (1<<num);
//                }
//                for (int j = 0; j < 3; j++) {
//                    for (int k = 0; k < 3; k++) {
//                        checkList[(node.x / 3) * 3 + j][(node.y / 3) * 3 + k] |= (1<<num);
//                    }
//                }
                board[node.x][node.y] = num;
                empty.remove(node);
            }
            else {
                empty.add(node);
                ArrayList<Node> changedList = new ArrayList<>();
                ArrayList<Node> removeList = new ArrayList<>();
                for (Node emp : empty) {
                    int bit = Integer.bitCount(checkList[emp.x][emp.y]);
                    if (emp.bitCount != bit){
                        changedList.add(new Node(emp.x,emp.y,bit));
                        removeList.add(emp);
                    }

                }
                for (Node emp : removeList) {
                    empty.remove(emp);
                }
                for (Node emp : changedList) {
                    empty.add(emp);
                }
                if (empty.first().bitCount != 8)
                    break;
            }

        }

    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sdoku[i][j] = System.in.read() - 48;
            }
            System.in.read();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sdoku[i][j];
                if (num == 0) continue;
                for (int k = 0; k < 9; k++) {
                    availList[k][j] |= (1<<num);
                    availList[i][k] |= (1<<num);
                }
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        availList[(i/3)*3 + k][(j/3)*3 + l] |= (1<<num);
                    }
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sdoku[i][j] == 0){
                    System.out.print("[" + i + "," + j + "]");
                    for (int k = 1; k <= 9; k++) {
                        if (((availList[i][j]>>k) & 1) == 0)
                            System.out.print(k);
                    }
                    System.out.println();
                }
            }
        }

        solve(availList,sdoku);


        for (int[] ints : sdoku) {
            System.out.println(Arrays.toString(ints));
        }

    }
}
/*
000500163
049013507
500620804
490105382
218000400
050208900
000000008
005706200
300900600

 */
