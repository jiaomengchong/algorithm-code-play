package com.jmc.algorithm.jjb.class27;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/2/15 20:20
 */
public class Code01_KMP {
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == s2.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] nexts = getNextArray(str2);
        int x = 0;
        int y = 0;

        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (y != 0) {
                y = nexts[y];
            } else {
                x++;
            }
        }

        return y == str2.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] str2) {
        if (str2.length < 2) {
            return new int[]{-1};
        }

        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int cnt = 0;
        int index = 2;

        while (index < str2.length) {
            if (str2[index - 1] == str2[cnt]) {
                next[index++] = ++cnt;
            } else if (cnt > 0) {
                cnt = next[cnt];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "acdactacdacd";
        String s2 = "acdacd";
        System.out.println(getIndexOf(s1, s2));
    }
}
