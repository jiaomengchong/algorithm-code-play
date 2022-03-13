package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

/**
 * 测试链接：https://leetcode-cn.com/problems/groups-of-strings/
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/11 21:15
 **/
public class Code05_GroupOfStrings {
    public static int[] groupOfStrings(String[] words) {

        return null;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"a", "b", "ab", "cde"};
        int N = words.length;
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            String str = words[i];
            char[] chars = str.toCharArray();
            for (char c : chars) {
                num[i] = c - 'a';
            }
        }
    }

    public static String print(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 31; i >= 0; i--) {
            sb.append(((1 << i) & num) != 0 ? "1" : "0");
        }
        return sb.toString();
    }
}