package org.example;

import java.util.*;
public class Programmers118670 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] answer;
        answer = s.solution(null,null);
    }

    static class Solution {
        public int[][] solution(int[][] rc, String[] operations) {
            rc = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
            operations = new String[]{"ShiftRow", "Rotate", "ShiftRow", "Rotate"};
            final int ROW = rc[0].length;
            ArrayDeque<ArrayDeque<Integer>> ans = new ArrayDeque<>();

            ArrayDeque<Integer> ansFirst = new ArrayDeque<>();
            ArrayDeque<Integer> ansLast = new ArrayDeque<>();

            for (int i = 0; i < rc.length; i++) {
                ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
                ansFirst.addLast(rc[i][0]);
                ansLast.addLast(rc[i][ROW-1]);
                for (int j = 1; j < ROW - 1; j++) {
                    arrayDeque.addLast(rc[i][j]);
                }
                ans.addLast(arrayDeque);
            }

            for (String str : operations) {
                if (str.equals("Rotate")){
                    ans.getFirst().addFirst(ansFirst.pollFirst());
                    ansLast.addFirst(ans.getFirst().pollLast());
                    ans.getLast().addLast(ansLast.pollLast());
                    ansFirst.addLast(ans.getLast().pollFirst());


//                    Iterator<ArrayDeque<Integer>> it = ans.iterator();
//                    ArrayDeque<Integer> pre = it.next();
//                    ArrayDeque<Integer> cnt = null;
//                    Integer preLast = pre.pollLast();
//                    Integer cntLast = null;
//                    for (int i = 1 ; i < ans.size() - 1; i++) {
//                        cnt = it.next();
//                        cntLast = cnt.pollLast();
//                        cnt.addLast(preLast);
//                        pre.addFirst(cnt.pollFirst());
//                        pre = cnt;
//                        preLast = cntLast;
//                    }
//                    cnt = it.next();
//                    cnt.addLast(preLast);
//                    pre.addFirst(cnt.pollFirst());
                } else if (str.equals("ShiftRow")) {
                    ans.addFirst(ans.pollLast());
                    ansFirst.addFirst(ansFirst.pollLast());
                    ansLast.addFirst(ansLast.pollLast());
                }
            }

            for (int i = 0; i < rc.length; i++) {
                rc[i][0] = ansFirst.pollFirst();
                ArrayDeque<Integer> arrayDeque = ans.pollFirst();
                for (int j = 1; j < ROW - 1; j++) {
                    rc[i][j] = arrayDeque.pollFirst();
                }
                rc[i][ROW-1] = ansLast.pollFirst();
            }

            for (int[] arr : rc) {
                System.out.println(Arrays.toString(arr));
            }

            return rc;
        }
    }
}
