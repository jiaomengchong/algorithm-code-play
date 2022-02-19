package com.jmc.algorithm.greatOffer.class01;

/**
 * 给定一个非负整数num，
 * 如何不用循环语句，
 * 返回>=num，并且离num最近的，2的某次方
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/24 16:22
 */
public class Code03_Near2Power {

    public static int near2Power(int num) {
        if (num < 0) {
            return 0;
        }
        num--;
        num |= num >> 1;
        num |= num >> 2;
        num |= num >> 4;
        num |= num >> 8;
        num |= num >> 16;
        return num + 1;
    }

    public static void main(String[] args) {
        int num = 120;
        System.out.println(near2Power(num));
    }
}
