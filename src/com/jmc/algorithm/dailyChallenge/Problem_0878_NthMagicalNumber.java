package com.jmc.algorithm.dailyChallenge;

import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/nth-magical-number/
 */
public class Problem_0878_NthMagicalNumber {
    public static int nthMagicalNumber(int n, int a, int b) {
        long ans = 0;
        int mod = 1000000007;
        // a=4 b=6 x=25
        // a=4 8 12 16 20 24
        // b=6 12 18 24
        // 重复2个：12 24
        // a有25/4=6个
        // b有25/6=4个
        // 总共6+4-2=8个
        // 最大公约数：2
        // 最小公倍数：12 = a*b/gcd
        int lcm = a * b / gcd(a, b);
        long left = 0, right = (long)Math.min(a, b) * n;
        long mid = 0;
        while (left <= right) {
            mid = right - (right - left) / 2;
            if (mid / a + mid / b - mid / lcm >= n) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) (ans % mod);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int n = 1000000000, a = 40000, b = 40000;
        System.out.println(gcd(a, b));
        System.out.println(nthMagicalNumber(n, a, b));
    }
}
