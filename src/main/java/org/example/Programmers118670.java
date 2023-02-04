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
            rc = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            operations = new String[]{"Rotate", "ShiftRow"};
            int[][] answer = rc;
            ArrayDeque<ArrayDeque<Integer>> ans = new ArrayDeque<>();

            for (int i = 0; i < rc.length; i++) {
                ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
                for (int j = 0; j < rc[i].length; j++) {
                    arrayDeque.addLast(rc[i][j]);
                }
                ans.addLast(arrayDeque);
            }

            for (String str : operations) {
                if (str.equals("Rotate")){
                    Iterator<ArrayDeque<Integer>> it = ans.iterator();
                    ArrayDeque<Integer> pre = it.next();
                    ArrayDeque<Integer> cnt = null;
                    Integer preLast = pre.pollLast();
                    Integer cntLast = null;
                    for (int i = 1 ; i < ans.size() - 1; i++) {
                        cnt = it.next();
                        cntLast = cnt.pollLast();
                        cnt.addLast(preLast);
                        pre.addFirst(cnt.pollFirst());
                        pre = cnt;
                        preLast = cntLast;
                    }
                    cnt = it.next();
                    cnt.addLast(preLast);
                    pre.addFirst(cnt.pollFirst());
                } else if (str.equals("ShiftRow")) {
                    ans.addFirst(ans.pollLast());
                }
            }

            for (int i = 0; i < answer.length; i++) {
                ArrayDeque<Integer> tmp = ans.pollFirst();
                answer[i] = tmp.stream().mapToInt(Integer::intValue).toArray();
            }

            for (int[] arr : rc) {
                System.out.println(Arrays.toString(arr));
            }

            return answer;
        }
    }
}
