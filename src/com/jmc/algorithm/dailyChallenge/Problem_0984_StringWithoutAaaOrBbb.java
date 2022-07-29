package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/string-without-aaa-or-bbb/
 */
public class Problem_0984_StringWithoutAaaOrBbb {
    public static String strWithout3a3b(int a, int b) {
        char pre = a >= b ? 'a' : 'b';
        int index = 0, total = a + b;
        StringBuffer sb = new StringBuffer();
        while (index < total) {
            if (pre == 'a') {
                for (int i = 0; i < Math.min(2, a); i++) {
                    sb.append('a');
                }
                index += Math.min(2, a);
                a -= Math.min(2, a);
                pre = 'b';
            } else {
                for (int i = 0; i < Math.min(2, b); i++) {
                    sb.append('b');
                }
                index += Math.min(2, b);
                b -= Math.min(2, b);
                pre = 'a';
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int a = 1, b = 4;
        System.out.println(strWithout3a3b(a, b));
    }
}
