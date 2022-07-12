package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/12 18:35
 **/
public class Problem_1281_SubtractTheProductAndSumOfDigitsOfAnInteger {
    public static int subtractProductAndSum(int n) {
        int product = 1, add = 0;
        while (n != 0) {
            product *= n % 10;
            add += n % 10;
            n /= 10;
        }

        return product - add;
    }

    public static void main(String[] args) {
        int n = 4421;
        System.out.println(subtractProductAndSum(n));
    }
}
