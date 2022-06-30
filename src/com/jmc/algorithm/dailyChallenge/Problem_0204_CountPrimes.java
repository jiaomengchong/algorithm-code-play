package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/count-primes/
 */
public class Problem_0204_CountPrimes {
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                ans++;
            }
        }
        return ans;
    }

    private static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
