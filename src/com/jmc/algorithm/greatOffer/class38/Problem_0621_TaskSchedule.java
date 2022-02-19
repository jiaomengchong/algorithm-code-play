package com.jmc.algorithm.greatOffer.class38;

import java.util.Map;

/**
 * 任务调度器
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/5 21:28
 **/
public class Problem_0621_TaskSchedule {
    public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        int N = tasks.length;
        int[] count = new int[256];
        int maxCount = 0;
        int maxCountKinds = 0;
        // 找出task出现次数最多的
        for (int i = 0; i < N; i++) {
            count[tasks[i]]++;
            maxCount = Math.max(maxCount, count[tasks[i]]);
        }
        // 找出task出现次数最多的种类
        for (int i = 0; i < 256; i++) {
            if (count[i] == maxCount) {
                maxCountKinds++;
            }
        }

        int restFinalTasks = N - maxCountKinds;
        int spaces = (n + 1) * (maxCount - 1);
        int restSpaces = Math.max(0, spaces - restFinalTasks);

        return N + restSpaces;
    }

    public static void main(String[] args) {
        char[] tasks = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
        System.out.println(1758973 % 1000);
    }
}
