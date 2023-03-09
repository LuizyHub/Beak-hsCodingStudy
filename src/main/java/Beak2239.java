import java.io.IOException;
import java.util.Arrays;

public class Beak2239 {
    private static int[][] availList = new int[9][9];
    private static int[][] sdoku = new int[9][9];
    private static class Node implements Comparable<Node>{
        public int idx;
        public int bitCount;

        public Node(int idx, int bitCount) {
            this.idx = idx;
            this.bitCount = bitCount;
        }

        @Override
        public int compareTo(Node o) {
            int dif = Integer.compare(o.bitCount, this.bitCount);
            if (dif == 0)
                return Integer.compare(this.idx, o.idx);
            return dif;
        }
    }
    private static void fill(){

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
                for (int k = (i/3)*3; k < 3; k++) {
                    for (int l = (j/3)*3; l < 3; l++) {
                        availList[k][l] |= (1<<num);
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
