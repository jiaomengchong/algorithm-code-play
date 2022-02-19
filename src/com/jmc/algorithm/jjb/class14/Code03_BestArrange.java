package com.jmc.algorithm.jjb.class14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/18 14:02
 */
public class Code03_BestArrange {
    public static class Program {
        private int start;
        private int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        return process(programs, 0, 0);
    }

    /**
     * 暴力递归函数
     *
     * @param programs
     * @param done     已完成的项目会议
     * @param timeLine 上一次项目会议的结束时间
     * @return
     */
    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }

        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                max = Math.max(max, process(copyButExcept(programs, i), done + 1, programs[i].end));
            }
        }

        return max;
    }

    private static Program[] copyButExcept(Program[] programs, int i) {
        Program[] copy = new Program[programs.length - 1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if (i != j) {
                copy[index++] = programs[j];
            }
        }

        return copy;
    }

    public static int bestArrange2(Program[] programs) {
        int ans = 0;
        if (programs == null || programs.length == 0) {
            return ans;
        }

        int preEnd = 0;
        Arrays.sort(programs, new MyComparator());
        for (int i = 0; i < programs.length; i++) {
            Program program = programs[i];
            if (program.start >= preEnd) {
                ans++;
                preEnd = program.end;
            }
        }

        return ans;
    }

    // for test
    private static Program[] generatorRandomProgramArray(int maxValue, int maxSize) {
        Program[] programs = new Program[(int) (Math.random() * (maxSize + 1))];

        for (int i = 0; i < programs.length; i++) {
            int start = (int) (Math.random() * (maxValue + 1));
            int end = (int) (Math.random() * (maxValue + 1));
            if (start == end) {
                programs[i] = new Program(start, end + 1);
            } else {
                programs[i] = new Program(Math.min(start, end), Math.max(start, end));
            }
        }

        return programs;
    }

    public static void main(String[] args) {
        int testTimes = 10_0000;
        int maxValue = 20;
        int maxSize = 12;

        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Program[] programs = generatorRandomProgramArray(maxValue, maxSize);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
