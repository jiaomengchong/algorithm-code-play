package com.jmc.algorithm.greatOffer.class40;

/**
 * 来自腾讯
 * 比如arr = {3,1,2,4}
 * 下标对应是：0 1 2 3
 * 你最开始选择一个下标进行操作，一旦最开始确定了是哪个下标，以后都只能在这个下标上进行操作
 * 比如你选定1下标，1下标上面的数字是1，你可以选择变化这个数字，比如你让这个数字变成2
 * 那么arr = {3,2,2,4}
 * 下标对应是：0 1 2 3
 * 因为你最开始确定了1这个下标，所以你以后都只能对这个下标进行操作，
 * 但是，和你此时下标上的数字一样的、且位置连成一片的数字，会跟着一起变
 * 比如你选择让此时下标1的数字2变成3，
 * 那么arr = {3,3,3,4} 可以看到下标1和下标2的数字一起变成3，这是规则！一定会一起变
 * 下标对应是：0 1 2 3
 * 接下来，你还是只能对1下标进行操作，那么数字一样的、且位置连成一片的数字(arr[0~2]这个范围)都会一起变
 * 决定变成4
 * 那么arr = {4,4,4,4}
 * 下标对应是：0 1 2 3
 * 至此，所有数都成一样的了，你在下标1上做了3个决定(第一次变成2，第二次变成3，第三次变成4)，
 * 因为联动规则，arr全刷成一种数字了
 * 给定一个数组arr，最开始选择哪个下标，你随意
 * 你的目的是一定要让arr都成为一种数字，注意联动效果会一直生效
 * 返回最小的变化数
 * arr长度 <= 200, arr中的值 <= 10^6
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/27 11:43
 **/
public class Code05_AllSame {
    public static int ways1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.min(ans, process1(arr, i - 1, arr[i], i + 1));
        }
        return ans;
    }

    private static int process1(int[] arr, int left, int midValue, int right) {
        for (; left >= 0 && arr[left] == midValue; ) {
            left--;
        }
        for (; right < arr.length && arr[right] == midValue; ) {
            right++;
        }

        if (left < 0 && right >= arr.length) {
            return 0;
        }

        int p1 = Integer.MAX_VALUE;
        if (left >= 0) {
            p1 = process1(arr, left - 1, arr[left], right) + 1;
        }

        int p2 = Integer.MAX_VALUE;
        if (right < arr.length) {
            p2 = process1(arr, left, arr[right], right + 1) + 1;
        }

        return Math.min(p1, p2);
    }

    public static int ways2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.min(ans, process2(arr, i - 1, i, i + 1));
        }
        return ans;
    }

    private static int process2(int[] arr, int left, int index, int right) {
        for (; left >= 0 && arr[left] == arr[index]; ) {
            left--;
        }
        for (; right < arr.length && arr[right] == arr[index]; ) {
            right++;
        }

        if (left == -1 && right == arr.length) {
            return 0;
        }

        int p1 = Integer.MAX_VALUE;
        if (left >= 0) {
            p1 = process2(arr, left - 1, left, right) + 1;
        }

        int p2 = Integer.MAX_VALUE;
        if (right < arr.length) {
            p2 = process2(arr, left, right, right + 1) + 1;
        }

        return Math.min(p1, p2);
    }

    public static int ways3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        int N = arr.length;
        int[][][] dp = new int[N + 2][N + 2][N + 2];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= N; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, process3(arr, i - 1, i, i + 1, dp));
        }

        return ans;
    }

    private static int process3(int[] arr, int left, int index, int right, int[][][] dp) {
        if (left != -1 && dp[left][index][right] != Integer.MAX_VALUE) {
            return dp[left][index][right];
        }

        for (; left >= 0 && arr[left] == arr[index]; ) {
            left--;
        }

        for (; right < arr.length && arr[right] == arr[index]; ) {
            right++;
        }

        if (left == -1 && right == arr.length) {
            return 0;
        }

        int p1 = Integer.MAX_VALUE;
        if (left >= 0) {
            p1 = process3(arr, left - 1, left, right, dp) + 1;
        }

        int p2 = Integer.MAX_VALUE;
        if (right < arr.length) {
            p2 = process3(arr, left, right, right + 1, dp) + 1;
        }

        if (left != -1) {
            dp[left][index][right] = Math.min(p1, p2);
        }
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{11113, 1, 111112, 4, 5, 4, 7, 8, 111112, 111112, 111112, 111112, 111112, 11113, 6, 11117, 8, 98, 9, 9, 9, 9, 9, 9, 11113, 1, 111112, 4, 5, 4, 7, 8, 111112, 111112, 111112, 111112, 111112, 11113, 6, 7, 8, 98, 9, 9, 9, 9, 9, 9};
        long start1 = System.currentTimeMillis();
        System.out.println(ways1(arr));
        long end1 = System.currentTimeMillis();
        System.out.println("ways1执行时间：" + (end1 - start1));

        System.out.println(ways2(arr));
        long end2 = System.currentTimeMillis();
        System.out.println("ways2执行时间：" + (end2 - end1));

        System.out.println(ways3(arr));
        long end3 = System.currentTimeMillis();
        System.out.println("ways3执行时间：" + (end3 - end2));
    }
}
