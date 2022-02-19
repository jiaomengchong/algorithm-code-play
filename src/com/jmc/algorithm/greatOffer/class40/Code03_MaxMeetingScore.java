package com.jmc.algorithm.greatOffer.class40;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定int[][] meetings，比如
 * {
 * {66, 70}   0号会议截止时间66，获得收益70
 * {25, 90}   1号会议截止时间25，获得收益90
 * {50, 30}   2号会议截止时间50，获得收益30
 * }
 * 一开始的时间是0，任何会议都持续10的时间，但是一个会议一定要在该会议截止时间之前开始
 * 只有一个会议室，任何会议不能共用会议室，一旦一个会议被正确安排，将获得这个会议的收益
 * 请返回最大的收益
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/27 15:54
 **/
public class Code03_MaxMeetingScore {
    public static class Info {
        private int endTime;
        private int score;

        public Info(int endTime, int score) {
            this.endTime = endTime;
            this.score = score;
        }
    }

    public static class MyComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.endTime - o2.endTime;
        }
    }

    public static int ways1(int[][] meetings) {
        if (meetings == null || meetings.length == 0 || meetings[0].length == 0) {
            return 0;
        }

        int N = meetings.length;
        Info[] infos = new Info[N];
        for (int i = 0; i < N; i++) {
            infos[i] = new Info(meetings[i][0], meetings[i][1]);
        }
        Arrays.sort(infos, new MyComparator());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int time = 0;
        for (int i = 0; i < N; i++) {
            if (time + 10 <= infos[i].endTime) {
                priorityQueue.add(infos[i].score);
                time += 10;
            } else {
                if (!priorityQueue.isEmpty() && priorityQueue.peek() < infos[i].score) {
                    priorityQueue.poll();
                    priorityQueue.add(infos[i].score);
                }
            }
        }

        int ans = 0;
        while (!priorityQueue.isEmpty()) {
            ans += priorityQueue.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] meetings = new int[][]{{72, 62}, {21, 218}, {17, 41}, {80, 12}, {78, 81}, {90, 338}, {71, 140}, {44, 159}, {59, 471}, {41, 285}, {33, 235}, {66, 433}};
        System.out.println(ways1(meetings));
    }
}
