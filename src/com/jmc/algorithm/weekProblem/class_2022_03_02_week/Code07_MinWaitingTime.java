package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

import java.util.PriorityQueue;

// 来自谷歌
// 给定一个数组arr，长度为n
// 表示n个服务员，每个人服务一个人的时间
// 给定一个正数m，表示有m个人等位
// 如果你是刚来的人，请问你需要等多久？
// 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？
public class Code07_MinWaitingTime {
    public static int ways1(int[] arr, int m) {
        PriorityQueue<int[]> serviceQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < arr.length; i++) {
            serviceQueue.add(new int[]{0, arr[i]});
        }
        for (int i = 0; i < m; i++) {
            int[] cur = serviceQueue.poll();
            serviceQueue.add(new int[]{cur[0] + cur[1], cur[1]});
        }
        return serviceQueue.peek()[0];
    }

    // 二分答案(最优解)
    public static int ways2(int[] arr, int m) {
        // arr = [2, 3, 5] 在6时刻范围内，他们分别能服务多少人
        // 2：0-2 2-4 4-6 6-8 能服务4个人
        // 3：0-3 3-6 6-9     能服务3个人
        // 5：0-5 5-10        能服务2个人
        // 由此可以看出，服务人数 = 时刻/arr[i]
        // 最好情况就是服务员人数大于客户人数m，所有客户不需要等待，在0时刻就可以得到服务
        // 最坏情况就是2这个服务员服务整个客户，最大等待时间就是2*m
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            best = Math.min(arr[i], best);
        }
        int ans = 0;
        int left = 0;
        int right = best * m;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cover = 0;
            for (int st : arr) {
                cover += (mid / st) + 1;
            }
            if (cover > m) {
                // 去左边二分
                right = mid - 1;
                ans = mid;
            } else {
                // 去右边二分
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 7, 9, 11};
        int m = 88;
        System.out.println(ways1(arr, m));
        System.out.println(ways2(arr, m));
    }
}
