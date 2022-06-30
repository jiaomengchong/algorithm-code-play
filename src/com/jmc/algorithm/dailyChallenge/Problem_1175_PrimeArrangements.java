package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/prime-arrangements/
 */
public class Problem_1175_PrimeArrangements {
    static int MOD = (int) (1e9 + 7);
    public static int numPrimeArrangements(int n) {
        int numPrime = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                numPrime++;
            }
        }
        int ans = (int) (cal(numPrime) * cal(n - numPrime) % MOD);
        return ans;
    }

    private static long cal(int num) {
        long res = 1;
        for (long i = 1; i <= num; i++) {
            res *= i;
            res = res % MOD;
        }
        return res;
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

    public static void main(String[] args) {
        int n = 100;
        System.out.println(numPrimeArrangements(n));
    }
}
