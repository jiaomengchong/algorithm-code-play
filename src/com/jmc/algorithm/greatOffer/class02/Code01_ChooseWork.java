package com.jmc.algorithm.greatOffer.class02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 给定数组hard和money，长度都为N
 * hard[i]表示i号的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力
 * 每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班
 * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/27 18:36
 */
public class Code01_ChooseWork {
    public static class Job {
        private int hard;
        private int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }

    public static int[] getMoneys(int[] hard, int[] money, int[] ability) {
        int[] ans = new int[ability.length];
        Job[] jobs = getJob(hard, money);
        Arrays.sort(jobs, new JobComparator());
        // 有序表
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        Job pre = jobs[0];
        treeMap.put(pre.hard, pre.money);
        for (Job job : jobs) {
            if (job.hard >= pre.hard && job.money > pre.money) {
                treeMap.put(job.hard, job.money);
                pre = job;
            }
        }
        // 利用有序表特性
        for (int i = 0; i < ability.length; i++) {
            Integer floorKey = treeMap.floorKey(ability[i]);
            int m = floorKey != null ? treeMap.get(floorKey) : 0;
            ans[i] = m;
        }
        return ans;
    }

    private static Job[] getJob(int[] hard, int[] money) {
        int N = hard.length;
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            Job job = new Job(hard[i], money[i]);
            jobs[i] = job;
        }
        return jobs;
    }

    public static void main(String[] args) {
        int[] hard = new int[]{3, 2, 6, 4, 2};
        int[] money = new int[]{20, 11, 50, 100, 12};
        int[] ability = new int[]{1, 2, 3, 5};
        int[] moneys = getMoneys(hard, money, ability);
        System.out.println(Arrays.toString(moneys));
    }
}
