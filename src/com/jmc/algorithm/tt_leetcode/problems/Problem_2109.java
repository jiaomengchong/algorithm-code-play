package com.jmc.algorithm.tt_leetcode.problems;

/**
 * @Author jmc
 * @Description
 * @Date 2021/12/23 19:15
 **/
public class Problem_2109 {
    public static String addSpaces(String s, int[] spaces) {
        int M = spaces.length;
        int N = s.length();
        StringBuffer sb = new StringBuffer();
        int pre = 0;
        for (int i = 0; i < M; i++) {
            sb.append(s, pre, spaces[i]);
            if (pre < N) {
                sb.append(" ");
            }
            pre = spaces[i];
        }
        if (pre < N) {
            sb.append(s, pre, N);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "spacing";
        int[] spaces = new int[]{0,1,2,3,4,5,6};
        System.out.println(addSpaces(s, spaces));
    }
}
