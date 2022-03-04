package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

import org.omg.CORBA.TRANSACTION_MODE;

/**
 * 来自微软
 * 给定一个正数num，要返回一个大于num的数，并且每一位和相邻位的数字不能相等
 * 返回达标的数字中，最小的那个
 * 10^9
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/3 19:06
 **/
public class Code02_NearBiggerNoSameNeighbour {
    public static int nearBiggerNum(int num) {
        // 678 -> 679
        // 100 -> 101
        // 999 -> 1010
        String str = String.format("0%s", num + 1);
        char[] chars = str.toCharArray();
        process(chars);
        return Integer.valueOf(String.valueOf(chars));
    }

    private static void process(char[] str) {
        for (int i = 1; i < str.length; i++) {
            if (str[i - 1] == str[i]) {
                addOne(str, i);
                // i后面全部刷成0 比如0998 ->1008 -> 1000
                for (int j = i + 1; j < str.length; j++) {
                    str[j] = '0';
                }
                process(str);
                return;
            }
        }
    }

    private static void addOne(char[] str, int i) {
        // 0998 - > 1008
        while (str[i] == '9') {
            str[i--] = '0';
        }
        str[i]++;
    }

    public static void main(String[] args) {
        char[] test = new char[] { '0', '1', '2', '3' };

        System.out.println(Integer.valueOf(String.valueOf(test)));

        int num1 = 55;
        System.out.println(nearBiggerNum(num1));

        int num2 = 1765;
        System.out.println(nearBiggerNum(num2));

        int num3 = 98;
        System.out.println(nearBiggerNum(num3));

        int num4 = 44432;
        System.out.println(nearBiggerNum(num4));

        int num5 = 3298;
        System.out.println(nearBiggerNum(num5));

        int num6 = 9999998;
        System.out.println(nearBiggerNum(num6));
    }
}