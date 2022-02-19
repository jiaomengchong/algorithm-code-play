package com.jmc.algorithm.greatOffer.class43;

import java.util.Arrays;
import java.util.Map;

/**
 * 微软：
 * 给定一个正数数组arr长度为n、正数x、正数y
 * 你的目标是让arr整体的累加和<=0
 * 你可以对数组中num执行以下三种操作中的一种，且每个数只能执行一次操作：
 * 1) 不变
 * 2) 可以选择让num变为0，承担x的代价
 * 3) 可以选择让num变成-num，承担y的代价
 * 返回你达到目标的最小代价
 * 数据规模：没有告知
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/2 17:15
 **/
public class Code01_SumNoPositiveMinCost {
    public static int ways1(int[] arr, int x, int y) {
        if (arr == null || arr.length == 0 || x < 0 || y < 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int ans = process(arr, x, y, 0, sum);
        return ans != Integer.MAX_VALUE ? ans : Integer.MAX_VALUE;
    }

    private static int process(int[] arr, int x, int y, int index, int rest) {
        if (index == arr.length) {
            return rest <= 0 ? 0 : Integer.MAX_VALUE;
        }

        if (rest <= 0) {
            return 0;
        }

        int ans;
        // 当前位置的数不变，去下个位置
        int p1 = process(arr, x, y, index + 1, rest);

        // 当前位置的数变为0
        int p2 = Integer.MAX_VALUE;
        int next2 = process(arr, x, y, index + 1, rest - arr[index]);
        if (next2 != Integer.MAX_VALUE) {
            p2 = x + next2;
        }

        // 当前位置的数变为-num
        int p3 = Integer.MAX_VALUE;
        int next3 = process(arr, x, y, index + 1, rest - (arr[index] << 1));
        if (next3 != Integer.MAX_VALUE) {
            p3 = y + next3;
        }

        ans = Math.min(p1, Math.min(p2, p3));
        return ans;
    }

    // 如果数据规模很大，动态规划解法不行，贪心(最优解)
    public static int ways2(int[] arr, int x, int y) {
        if (arr == null || arr.length == 0 || x < 0 || y < 0) {
            return 0;
        }

        int N = arr.length;
        int ans = 0;
        Arrays.sort(arr);
        if (x >= y) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += arr[i];
            }

            for (int i = N - 1; i >= 0; i--) {
                if (sum <= 0) {
                    return ans;
                }
                sum -= arr[i] << 1;
                ans += y;
            }
        } else {
            // 初始情况认为都用了变0操作，每一步都花费了x
            ans = N * x;
            // 生成前缀和
            for (int i = 1; i < N; i++) {
                arr[i] += arr[i - 1];
            }
            int cost = 0;
            for (int i = N - 1; i > 0; i--) {
                cost += arr[i] - arr[i - 1];
                int mostRight = mostRight(arr, i, cost);
                ans = Math.min(ans, (i > mostRight ? i - mostRight - 1 : 0) * x + (N - i) * y);
            }
        }

        return ans;
    }

    // arr是前缀和
    private static int mostRight(int[] arr, int r, int cost) {
        int index = 0;
        int l = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= cost) {
                l = mid + 1;
                index = mid;
            } else {
                r = mid - 1;
            }
        }
        return index;
    }

    public static int ways3(int[] arr, int x, int y) {
        if (arr == null || arr.length == 0 || x < 0 || y < 0) {
            return 0;
        }

        Arrays.sort(arr);
        int N = arr.length;
        int ans = 0;
        if (x >= y) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += arr[i];
            }
            for (int i = N - 1; i >= 0; i--) {
                if (sum <= 0) {
                    return ans;
                }
                ans += y;
                sum -= arr[i] << 1;
            }

            return ans;
        }

        // x < y,有讨论的可能性
        // 利用不回退技巧[1,12,23,36,44,55,66]
        int hold = 0;
        int suffixSum = 0;
        ans = N * x;
        for (int yLeft = N - 1, xRight = 0; yLeft >= xRight; yLeft--) {
            suffixSum += arr[yLeft];
            while (xRight < yLeft && arr[xRight] + hold <= suffixSum) {
                hold += arr[xRight++];
            }
            ans = Math.min(ans, (N - yLeft) * y + (yLeft - xRight) * x);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 15, 8, 4, 11, 19, 19, 7, 10, 20};
        int x = 3;
        int y = 4;
        System.out.println(ways1(arr, x, y));

        int[] copy1 = Arrays.copyOf(arr, arr.length);
        System.out.println(ways2(copy1, x, y));

        int[] copy2 = Arrays.copyOf(arr, arr.length);
        System.out.println(ways3(copy2, x, y));

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(mostRight(arr, arr.length - 1, 66));
    }
}
