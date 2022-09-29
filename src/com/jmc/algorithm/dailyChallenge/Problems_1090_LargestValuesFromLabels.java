package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/largest-values-from-labels/
 */
public class Problems_1090_LargestValuesFromLabels {
    public static class Info {
        public int value;
        public int label;

        public Info(int value, int label) {
            this.value = value;
            this.label = label;
        }
    }

    public static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int N = values.length, index = 0, ans = 0;
        Info[] infos = new Info[N];
        for (int i = 0; i < N; i++) {
            Info info = new Info(values[i], labels[i]);
            infos[index++] = info;
        }

        Arrays.sort(infos, (a, b) -> b.value - a.value);
        Map<Integer, Integer> useMap = new HashMap<>();
        for (int i = 0; i < N && numWanted > 0; i++) {
            if (!useMap.containsKey(infos[i].label)) {
                useMap.put(infos[i].label, 1);
                ans += infos[i].value;
                numWanted--;
            } else if (useMap.get(infos[i].label) < useLimit) {
                useMap.put(infos[i].label, useMap.get(infos[i].label) + 1);
                ans += infos[i].value;
                numWanted--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [9,6,9,1,3]
        // [0,1,2,0,0]
        // 4
        // 4
        int[] values = new int[]{9, 6, 9, 1, 3};
        int[] labels = new int[]{0, 1, 2, 0, 0};
        int numWanted = 4, useLimit = 4;
        System.out.println(largestValsFromLabels(values, labels, numWanted, useLimit));
    }
}
