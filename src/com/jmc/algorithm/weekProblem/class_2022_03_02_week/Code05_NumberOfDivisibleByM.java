package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

// 来自微软
// 给定一个数组arr，给定一个正数M
// 如果arr[i] + arr[j]可以被M整除，并且i < j，那么(i,j)叫做一个M整除对
// 返回arr中M整除对的总数量
public class Code05_NumberOfDivisibleByM {
    public static int ways1(int[] arr, int M) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if ((arr[i] + arr[j]) % M == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int ways2(int[] arr, int M) {
        int[] counts = new int[M];
        int n = arr.length;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = (arr[i] % M) < 0 ? (arr[i] % M) + M : arr[i] % M;
            ans += counts[(M - cur) % M];
            counts[cur]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, -4, 0, 3, -3};
        int M = 7;
        System.out.println(ways1(arr, M));
        System.out.println(ways2(arr, M));
    }
}