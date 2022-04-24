package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/
 */
public class Problem_0762_PrimeNumberOfSetBitsInBinaryRepresentation {
    public static int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int tmp = i;
            int count = 0;
            while (tmp != 0) {
                int rightOne = tmp & (-tmp);
                tmp ^= rightOne;
                count++;
            }
            System.out.println(count);
            ans += isPrime(count) ? 1 : 0;
        }
        return ans;
    }

    public static boolean isPrime(int num) {
        if (num == 1 || num <= 0) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int left = 10;
        int right = 15;
        System.out.println(countPrimeSetBits(left, right));
        System.out.println(isPrime(4));
    }
}
