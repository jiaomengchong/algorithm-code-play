package com.jmc.algorithm.greatOffer.class09;

/**
 * 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
 * 问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 * <p>
 * 问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/26 10:14
 */
public class Code01_LightProblem {
    public static int noLoopWays1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        // 0位置：不按开关按钮
        int p1 = noLoopProcess1(arr, 2, arr[0], arr[1]);
        // 0位置：按开关按钮
        int p2 = noLoopProcess1(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    private static int noLoopProcess1(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) {
            return preStatus == curStatus ? preStatus ^ 1 : Integer.MAX_VALUE;
        }
        // 前一个状态为0，当前位置一定要按下开关按钮，否则就没有补救机会了
        if (preStatus == 0) {
            preStatus = curStatus ^ 1;
            int result = noLoopProcess1(arr, nextIndex + 1, preStatus, arr[nextIndex] ^ 1);
            return result == Integer.MAX_VALUE ? result : result + 1;
        } else {
            // 前一个状态为1，当前位置不要按下开关按钮
            return noLoopProcess1(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }

    public static int noLoopWays2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return (arr[0] ^ arr[1]) == 0 ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        // 0位置不按开关按钮
        int p1 = noLoopProcess2(arr, 2, arr[0], arr[1]);
        // 0位置按开关按钮
        int p2 = noLoopProcess2(arr, 2, arr[0] ^ 1, arr[1] ^ 1);

        return Math.min(p1, p2 != Integer.MAX_VALUE ? p2 + 1 : p2);
    }

    private static int noLoopProcess2(int[] arr, int nextIndex, int preStatus, int curStatus) {
        int op = 0;
        while (nextIndex != arr.length) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = arr[nextIndex++] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[nextIndex++];
            }
        }
        return preStatus == curStatus ? op + (curStatus ^ 1) : Integer.MAX_VALUE;
    }

    public static int loopWays1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[1] != arr[0] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        if (arr.length == 3) {
            return arr[0] != arr[1] || arr[1] != arr[2] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 0和1位置组合有4种情况：00 01 10 11
        // 0位置不作为普遍位置，是因为环形，N - 1能补救0位置
        // 2 ~ N - 2位置作为普遍位置，它们的preStatus为0，必须按钮开关按钮，如果1不按开关
        int N = arr.length;
        // 00
        int p1 = loopProcess1(arr, 3, arr[1], arr[2], arr[0], arr[N - 1]);
        // 01
        int p2 = loopProcess1(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[0] ^ 1, arr[N - 1]);
        // 10
        int p3 = loopProcess1(arr, 3, arr[1] ^ 1, arr[2], arr[0] ^ 1, arr[N - 1] ^ 1);
        // 11
        int p4 = loopProcess1(arr, 3, arr[1], arr[2] ^ 1, arr[0], arr[N - 1] ^ 1);

        p2 = (p2 != Integer.MAX_VALUE) ? (p2 + 1) : p2;
        p3 = (p3 != Integer.MAX_VALUE) ? (p3 + 1) : p3;
        p4 = (p4 != Integer.MAX_VALUE) ? (p4 + 2) : p4;

        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    private static int loopProcess1(int[] arr, int nextIndex, int preStatus, int curStatus, int firstStatus, int endStatus) {
        if (nextIndex == arr.length) {
            return (firstStatus != endStatus || firstStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }

        int noPreStatus = 0;
        int yesPreStatus = 0;
        int noCurStatus = 0;
        int yesCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if (nextIndex < arr.length - 1) {
            noPreStatus = curStatus;
            yesPreStatus = curStatus ^ 1;
            noCurStatus = arr[nextIndex];
            yesCurStatus = arr[nextIndex] ^ 1;
        } else {
            noPreStatus = curStatus;
            yesPreStatus = curStatus ^ 1;
            noCurStatus = endStatus;
            yesCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }

        if (preStatus == 0) {
            int ans = loopProcess1(arr, nextIndex + 1, yesPreStatus, yesCurStatus, firstStatus,
                    nextIndex == arr.length - 1 ? yesEndStatus : endStatus);
            return ans != Integer.MAX_VALUE ? ans + 1 : ans;
        } else {
            return loopProcess1(arr, nextIndex + 1, noPreStatus, noCurStatus, firstStatus,
                    nextIndex == arr.length - 1 ? noEndStatus : endStatus);
        }
    }

    public static int loopWays2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1 ^ arr[0];
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        if (arr.length == 3) {
            return (arr[0] == arr[1] && arr[1] == arr[2]) ? (arr[0] ^ 1) : Integer.MAX_VALUE;
        }

        int N = arr.length;
        // 第一种00
        int p1 = loopWays2(arr, arr[1], arr[2], arr[0], arr[N - 1]);
        // 第二种01
        int p2 = loopWays2(arr, arr[1] ^ 1, arr[2] ^ 1, arr[0] ^ 1, arr[N - 1]);
        // 第三种10
        int p3 = loopWays2(arr, arr[1] ^ 1, arr[2], arr[0] ^ 1, arr[N - 1] ^ 1);
        // 第四种11
        int p4 = loopWays2(arr, arr[1], arr[2] ^ 1, arr[0], arr[N - 1] ^ 1);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        p3 = (p3 == Integer.MAX_VALUE) ? p3 : (p3 + 1);
        p4 = (p4 == Integer.MAX_VALUE) ? p4 : (p4 + 2);

        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    private static int loopWays2(int[] arr, int preStatus, int curStatus, int firstStatus, int endStatus) {
        int nextIndex = 3;
        int op = 0;
        while (nextIndex < arr.length - 1) {
            if (preStatus == 0) {
                op++;
                // 当前需要按下开关按钮
                preStatus = curStatus ^ 1;
                curStatus = arr[nextIndex++] ^ 1;
            } else {
                // 当前不需要按下开关按钮
                preStatus = curStatus;
                curStatus = arr[nextIndex++];
            }
        }

        // nextIndex来到了arr.length - 1，当前来到了N - 2
        if (preStatus == 0) {
            op++;
            // 需要按下开关按钮
            preStatus = curStatus ^ 1;
            endStatus = endStatus ^ 1;
            curStatus = endStatus;
        } else {
            // 不需要按下开关按钮
            preStatus = curStatus;
            curStatus = endStatus;
        }
        // nextIndex来到了arr.length - 1
        op = (preStatus == endStatus && endStatus == firstStatus) ? (op + (endStatus ^ 1)) : Integer.MAX_VALUE;
        return op;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 1, 0, 0, 1, 0, 0};
        System.out.println(noLoopWays1(arr));
        System.out.println(noLoopWays2(arr));
        System.out.println(loopWays1(arr));
        System.out.println(loopWays2(arr));
    }
}
