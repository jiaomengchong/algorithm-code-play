package com.jmc.algorithm.weekProblem.class_2022_02_3_week;

/**
 * 测试链接：https://leetcode-cn.com/problems/robot-bounded-in-circle/description/
 *
 * @Author jmc
 * @Description
 * @Date 2022/2/28 21:09
 **/
public class Code02_RobotBoundedInCircleDescription {
    public static boolean isRobotBounded(String instructions) {
        if (instructions == null || instructions.length() == 0) {
            return true;
        }

        // 0123四个方向
        int direction = 0;
        int row = 0;
        int col = 0;

        char[] chars = instructions.toCharArray();
        for (char ch : chars) {
            if (ch == 'R') {
                direction = right(direction);
            } else if (ch == 'L') {
                direction = left(direction);
            } else {
                row = row(direction, row);
                col = col(direction, col);
            }
        }

        return (row == 0 && col == 0) || direction != 0 ? true : false;
    }

    private static int col(int direction, int col) {
        return direction == 0 || direction == 2 ? direction : (direction > 1 ? col - 1 : col + 1);
    }

    private static int row(int direction, int row) {
        return direction == 1 || direction == 3 ? direction : (direction > 0 ? row - 1 : row + 1);
    }

    private static int left(int direction) {
        return direction == 0 ? 3 : direction - 1;
    }

    private static int right(int direction) {
        return direction == 3 ? 0 : direction + 1;
    }

    public static void main(String[] args) {
        String instructions = "GL";
        System.out.println(isRobotBounded(instructions));
    }
}
