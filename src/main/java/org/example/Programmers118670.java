package org.example;

import java.util.ArrayDeque;
import java.util.Arrays;

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

            for (int i = 0; i < answer.length; i++) {
                ans.addLast(new ArrayDeque(Arrays.asList(answer[i])));
            }

            for (String str : operations) {
                if (str.equals("Rotate")){

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
