package com.jmc.algorithm.jjb.class14;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * <p>
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * <p>
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/18 9:55
 */
public class Code02_LessMoneySplitGold {

    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process1(arr, 0);
    }

    public static int process1(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 合并数组
                ans = Math.min(process1(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]), ans);
            }
        }

        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        ans[ansi] = arr[i] + arr[j];
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[++ansi] = arr[arri];
            }
        }
        return ans;
    }

    public static int lessMoney2(int[] arr) {
        int ans = 0;
        if (arr == null || arr.length == 0) {
            return ans;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }

        int cur = 0;
        while (priorityQueue.size() > 1) {
            Integer first = priorityQueue.poll();
            Integer second = priorityQueue.poll();
            cur = first + second;
            ans += cur;
            priorityQueue.add(cur);
        }

        return ans;
    }


    // for test
    private static int[] generateRandomArray(int maxValue, int maxSize) {
        int arraySize = (int) (Math.random() * maxSize);
        int[] arr = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            int randomValue = (int) (Math.random() * maxValue);
             arr[i]= randomValue;
        }

        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 10_0000;
        int maxValue = 100;
        int maxSize = 6;

        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] randomArray = generateRandomArray(maxValue, maxSize);
            if (lessMoney1(randomArray) != lessMoney2(randomArray)) {
                System.out.println("Oops!");
            }
        }

        System.out.println("finish!");
    }
}
