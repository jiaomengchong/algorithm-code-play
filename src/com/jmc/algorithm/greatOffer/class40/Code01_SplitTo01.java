package com.jmc.algorithm.greatOffer.class40;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯
 * 分裂问题
 * 一个数n，可以分裂成一个数组[n/2, n%2, n/2]
 * 这个数组中哪个数不是1或者0，就继续分裂下去
 * 比如 n = 5，一开始分裂成[2, 1, 2]
 * [2, 1, 2]这个数组中不是1或者0的数，会继续分裂下去，比如两个2就继续分裂
 * [2, 1, 2] -> [1, 0, 1, 1, 1, 0, 1]
 * 那么我们说，5最后分裂成[1, 0, 1, 1, 1, 0, 1]
 * 每一个数都可以这么分裂，在最终分裂的数组中，假设下标从1开始
 * 给定三个数n、l、r，返回n的最终分裂数组里[l,r]范围上有几个1
 * n <= 2 ^ 50，n是long类型
 * r - l <= 50000，l和r是int类型
 * 我们的课加个码:
 * n是long类型随意多大都行
 * l和r也是long类型随意多大都行，但要保证l<=r
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/26 11:41
 **/
public class Code01_SplitTo01 {
    public static long ways1(long n, long l, long r) {
        if (n == 1 || n == 0) {
            return n == 1 ? 1 : 0;
        }

        long half = size(n >> 1);
        long left = l > half ? 0 : ways1(n >> 1, l, Math.min(half, r));
        long mid = (l > half + 1 || r <= half) ? 0 : n & 1;
        long right = r <= half + 1 ? 0 : ways1(n >> 1, Math.max(1, l - half - 1), r - half - 1);
        return left + mid + right;
    }

    private static long size(long n) {
        if (n == 1 || n == 0) {
            return 1;
        }

        return (size(n >> 1) << 1) + 1;
    }

    public static long ways2(long n, long l, long r) {
        Map<Long, Long> dp = new HashMap<>();
        return dp(n, l, r, dp);
    }

    private static long dp(long n, long l, long r, Map<Long, Long> dp) {
        if (n == 1 || n == 0) {
            return n == 1 ? 1 : 0;
        }

        long half = size(n >> 1);
        long all = (half << 1) + 1;
        if (l == 1 && r >= all) {
            if (dp.containsKey(n)) {
                return dp.get(n);
            } else {
                long count = dp(n >> 1, 1, half, dp);
                long ans = (count << 1) + (n & 1);
                dp.put(n, ans);
                return ans;
            }
        } else {
            long left = l > half ? 0 : dp(n >> 1, l, Math.min(half, r), dp);
            long mid = (l > half + 1 || r < half + 1) ? 0 : (n & 1);
            long right = r > half + 1 ? dp(n >> 1, Math.max(1, l - half - 1), r - half - 1, dp) : 0;
            return left + mid + right;
        }
    }

    public static void main(String[] args) {
        long n = (2L << 50) + 22517998136L;
        long l = 30000L;
        long r = 800000200L;
        long start = System.currentTimeMillis();
        System.out.println(ways1(n, l, r));
        long end = System.currentTimeMillis();
        System.out.println("ways1 花费时间：" + (end - start));

        n = (2L << 55) + 22517998136L;
        l = 30000L;
        r = 6431000002000L;
        long start1 = System.currentTimeMillis();
        System.out.println(ways2(n, l, r));
        long end1 = System.currentTimeMillis();
        System.out.println("ways1 花费时间：" + (end1 - start1));
    }
}
