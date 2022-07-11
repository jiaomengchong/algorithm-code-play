package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-students-doing-homework-at-a-given-time/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/11 18:56
 **/
public class Problem_1450_NumberOfStudentsDoingHomeworkAtAGivenTime {
    public static int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int N = startTime.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] startTime = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] endTime = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10};
        int queryTime = 5;
        System.out.println(busyStudent(startTime, endTime, queryTime));
    }
}
