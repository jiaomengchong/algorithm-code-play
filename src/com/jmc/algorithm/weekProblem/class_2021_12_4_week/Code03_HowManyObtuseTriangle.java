package com.jmc.algorithm.weekProblem.class_2021_12_4_week;

import java.util.Arrays;

/**
 * 来自hulu
 * 有一个以原点为圆心，半径为1的圆
 * 在这个圆的圆周上，有一些点
 * 因为所有的点都在圆周上，所以每个点可以有很简练的表达
 * 比如：用0来表示一个圆周上的点，这个点就在(1,0)位置
 * 比如：用6000来表示一个点，这个点是(1,0)点沿着圆周逆时针转60.00度之后所在的位置
 * 比如：用18034来表示一个点，这个点是(1,0)点沿着圆周逆时针转180.34度之后所在的位置
 * 这样一来，所有的点都可以用[0, 36000)范围上的数字来表示
 * 那么任意三个点都可以组成一个三角形，返回能组成钝角三角形的数量
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/29 14:21
 **/
public class Code03_HowManyObtuseTriangle {
    public static int obtuseTriangles(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int M = N << 1;
        int[] all = new int[M];
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            all[i] = arr[i];
            all[i + N] = arr[i] + 36000;
        }

        int ans = 0;
        for (int L = 0, R = 0; L < N; L++) {
            while (R < M && all[R] - all[L] <= 18000) {
                R++;
            }
            ans += num(R - L - 1);
        }

        return ans;
    }

    private static int num(int num) {
        return num == 2 ? 1 : num * (num - 1) >> 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1000, 17000, 18700, 18800, 25600, 27800, 30000, 30600, 35900};
        System.out.println(obtuseTriangles(arr));
        System.out.println(Math.atan2(2, 2));
        System.out.println(Math.toDegrees(Math.atan2(-2, -2)));
    }
}
