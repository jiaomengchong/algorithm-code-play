package com.jmc.algorithm.greatOffer.class41;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 来自小红书
// 有四种诗的韵律分别为: AABB、ABAB、ABBA、AAAA
// 比如 : 1 1 3 3就属于AABB型的韵律、6 6 6 6就属于AAAA型的韵律等等
// 一个数组arr，当然可以生成很多的子序列，如果某个子序列一直以韵律的方式连接起来，我们称这样的子序列是有效的
// 比如, arr = { 1, 1, 15, 1, 34, 1, 2, 67, 3, 3, 2, 4, 15, 3, 17, 4, 3, 7, 52, 7, 81, 9, 9 }
// arr的一个子序列为{1, 1, 1, 1, 2, 3, 3, 2, 4, 3, 4, 3, 7, 7, 9, 9}
// 其中1, 1, 1, 1是AAAA、2, 3, 3, 2是ABBA、4, 3, 4, 3是ABAB、7, 7, 9, 9是AABB
// 可以看到，整个子序列一直以韵律的方式连接起来，所以这个子序列是有效的
// 给定一个数组arr, 返回最长的有效子序列长度
// 题目限制 : arr长度 <= 4000, arr中的值<= 10^9
// 离散化之后，arr长度 <= 4000,  arr中的值<= 4000
public class Code02_PoemProblem {
    public static int ways2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[] copy = Arrays.copyOf(arr, N);
        Arrays.sort(copy);
        // arr =[900,3,7,3,900,1,900,1]
        // copy=[1,1,3,3,7,900,900,900]
        // 离散化
        Map<Integer, Integer> indexMap = new HashMap<>();
        int index = 0;
        indexMap.put(copy[0], index++);
        for (int i = 1; i < N; i++) {
            if (copy[i] != copy[i - 1]) {
                indexMap.put(copy[i], index++);
            }
        }

        // 二维数组，一维是离散化的个数，二维是每个离散化值出现的下标
        // [
        //   [5,7]
        //   [1,3]
        //   [2]
        //   [0,4,6]
        // ]
        int[] sizeArr = new int[index];
        for (int i = 0; i < N; i++) {
            arr[i] = indexMap.get(arr[i]);
            sizeArr[arr[i]]++;
        }

        // 初始化离散二维数组
        int[][] mapArr = new int[index][];
        for (int i = 0; i < index; i++) {
            mapArr[i] = new int[sizeArr[i]];
        }
        for (int i = N - 1; i >= 0; i--) {
            mapArr[arr[i]][--sizeArr[arr[i]]] = i;
        }

        return process(arr, mapArr, 0);
    }

    private static int process(int[] arr, int[][] mapArr, int index) {
        if (index + 4 > arr.length) {
            return 0;
        }

        // 不要index位置
        int p0 = process(arr, mapArr, index + 1);

        // AABB,index位置作为A,那要找到在index位置右边第一次出现A的位置
        int p1 = 0;
        int nextA2 = nextIndex(mapArr, arr[index], index);
        if (nextA2 != -1) {
            for (int next = nextA2 + 1; next < arr.length; next++) {
                if (arr[next] != arr[index]) {
                    int nextB2 = nextIndex(mapArr, arr[next], next);
                    if (nextB2 != -1) {
                        p1 = Math.max(p1, 4 + process(arr, mapArr, nextB2 + 1));
                    }
                }
            }
        }

        // ABAB
        int p2 = 0;
        for (int nextB = index + 1; nextB < arr.length; nextB++) {
            if (arr[nextB] != arr[index]) {
                int nextABA2 = nextIndex(mapArr, arr[index], nextB);
                if (nextABA2 != -1) {
                    int nextB2 = nextIndex(mapArr, arr[nextB], nextABA2);
                    if (nextB2 != -1) {
                        p2 = Math.max(p2, 4 + process(arr, mapArr, nextB2 + 1));
                    }
                }
            }
        }

        // ABBA
        int p3 = 0;
        for (int nextB = index + 1; nextB < arr.length; nextB++) {
            if (arr[index] != arr[nextB]) {
                int nextB2 = nextIndex(mapArr, arr[nextB], nextB);
                if (nextB2 != -1) {
                    int nextA = nextIndex(mapArr, arr[index], nextB);
                    if (nextA != -1) {
                        p3 = Math.max(p3, 4 + process(arr, mapArr, nextA + 1));
                    }
                }
            }
        }

        // AAAA
        int p4 = 0;
        int p4A2 = nextIndex(mapArr, arr[index], index);
        int p4A3 = p4A2 == -1 ? -1 : nextIndex(mapArr, arr[index], p4A2);
        int p4A4 = p4A3 == -1 ? -1 : nextIndex(mapArr, arr[index], p4A3);
        if (p4A4 != -1) {
            p4 = 4 + process(arr, mapArr, p4A4 + 1);
        }

        return Math.max(p0, Math.max(Math.max(p1, p2), Math.max(p3, p4)));
    }

    private static int nextIndex(int[][] mapArr, int value, int index) {
        int left = 0;
        int right = mapArr[value].length - 1;
        int ans = -1;
        while (right >= left) {
            int mid = (left + right) >> 1;
            if (mapArr[value][mid] <= index) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans == -1 ? -1 : mapArr[value][ans];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 15, 1, 34, 1, 2, 67, 3, 3, 2, 4, 15, 3, 17, 4, 3, 7, 52, 7, 81, 9, 9};
        System.out.println(ways2(arr));
    }
}
