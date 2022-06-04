package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/consecutive-numbers-sum/
 */
public class Problem_0829_ConsecutiveNumbersSum {
    public static int consecutiveNumbersSum(int n) {
        // 输入: n = 5
        // 输出: 2
        // 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
        int L = 1;
        int R = 1;
        int ans = 0;
        int sum = 0;
        while (R <= n) {
            sum += R;
            while (sum > n) {
                sum -= L;
                L++;
            }
            if (sum == n) {
                ans++;
            }
            R++;
        }
        return ans;
    }

    public static int consecutiveNumbersSum2(int n) {
        // 等差数列公式(a+a+k-1)*k/2=sum
        // 2a+k-1=2sum/k
        // 2a=2sum/k-(k-1) 由于a>=1
        // 2sum/k-(k-1)>=2
        // 2sum/k>=k+1
        // 2sum/k>=k ==> 2sum>=k*k
        int ans = 0;
        n <<= 1;
        for (int k = 1; k * k < n; k++) {
            if (n % k != 0) {
                continue;
            }
            if ((n / k - (k - 1)) % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(n << 1);
        System.out.println(consecutiveNumbersSum(n));
        System.out.println(consecutiveNumbersSum2(n));
    }
}
