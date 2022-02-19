package com.jmc.algorithm.greatOffer.class39;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * 来自腾讯
 * 给定一个只由0和1组成的字符串S，假设下标从1开始，规定i位置的字符价值V[i]计算方式如下：
 * 1) i = 1时，V[i] = 1
 * 2) i > 1时，如果S[i] != S[i-1]，V[i] = 1
 * 2) i > 1时，如果S[i] == S[i-1]，V[i] = V[i-1] + 1
 * 你可以随意删除字符串S中的字符，返回整个S中的最大价值
 * 字符串长度<=5000
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/15 11:49
 **/
public class Code01_AddValue {
    public static int ways1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        return 1 + process1(str, 1, str[0], 1);
    }

    private static int process1(char[] str, int index, char lastChar, int preValue) {
        if (index == str.length) {
            return 0;
        }

        // 01011100
        int p1 = process1(str, index + 1, lastChar, preValue);
        int next = process1(str, index + 1, str[index], lastChar == str[index] ? preValue + 1 : 1);
        int p2 = (lastChar == str[index] ? preValue + 1 : 1) + next;
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        String s = "0000000111111100110101";
        //          1201234
        System.out.println(ways1(s));
    }
}
