package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/valid-square/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/29 14:53
 **/
public class Problems_0593_ValidSquare {
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double distance12 = getDistance(p1, p2);
        double distance13 = getDistance(p1, p3);
        double distance23 = getDistance(p2, p3);
        double distance24 = getDistance(p2, p4);
        double distance34 = getDistance(p3, p4);
        double distance14 = getDistance(p1, p4);

        // 点1 2 3
        if (check2(distance12, distance13, distance23)) return false;

        // 点2 3 4
        if (check2(distance23, distance24, distance34)) return false;

        // 点3 4 1
        if (check2(distance34, distance13, distance14)) return false;

        // 点4 1 2
        if (check2(distance14, distance24, distance12)) return false;

        return true;
    }

    private static boolean check2(double distance12, double distance13, double distance23) {
        double[] edges1 = getEdges(distance12, distance13, distance23);
        if (!check(distance12, distance13, distance23, edges1)) {
            return true;
        }
        return false;
    }

    private static boolean check(double a, double b, double c, double[] edges) {
        // 斜边数
        int hypotenuseCnt = 0;
        // 直边数
        int straightCnt = 0;
        if (a == edges[0] || a == edges[1]) {
            if (a == edges[0]) {
                hypotenuseCnt++;
            } else {
                straightCnt++;
            }
        }
        if (b == edges[0] || b == edges[1]) {
            if (b == edges[0]) {
                hypotenuseCnt++;
            } else {
                straightCnt++;
            }
        }
        if (c == edges[0] || c == edges[1]) {
            if (c == edges[0]) {
                hypotenuseCnt++;
            } else {
                straightCnt++;
            }
        }
        if (hypotenuseCnt != 1 || straightCnt != 2) {
            return false;
        }

        if (edges[1] * 2 != edges[0]) {
            return false;
        }

        return true;
    }

    private static double[] getEdges(double distance12, double distance13, double distance23) {
        double hypotenuse = Math.max(Math.max(distance12, distance13), distance23);
        double straight = Math.min(Math.min(distance12, distance13), distance23);
        return new double[]{hypotenuse, straight};
    }

    private static double getDistance(int[] p1, int[] p2) {
        return Math.pow((p2[0] - p1[0]), 2) + Math.pow(p2[1] - p1[1], 2);
    }

    public static void main(String[] args) {
        int[] p1 = new int[]{0, 0}, p2 = new int[]{1, 1}, p3 = new int[]{1, 0}, p4 = new int[]{0, 1};
        System.out.println(validSquare(p1, p2, p3, p4));
    }
}
