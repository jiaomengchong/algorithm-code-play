package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 测试链接：https://leetcode-cn.com/problems/minimum-genetic-mutation/
 */
public class Problem_0433_MinimumGeneticMutation {
    static int steps = 0;
    public static int minMutation(String start, String end, String[] bank) {
        Set<String> sets = new HashSet<>();
        for (String mutation : bank) {
            sets.add(mutation);
        }

        process(start.toCharArray(), end.toCharArray(), 0, start.length(), sets);
        return steps;
    }

    private static void process(char[] start, char[] end, int index, int N, Set<String> sets) {
        if (index == N) {
            return;
        }

        if (start[index] != end[index]) {
            char tmp = start[index];
            start[index] = end[index];
            steps += sets.contains(String.valueOf(start)) ? 1 : 0;
            process(start, end, index + 1, N, sets);
            start[index] = tmp;
        } else {
            process(start, end, index + 1, N, sets);
        }
    }

    public static int minMutation1(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }

        Set<String> sets = new HashSet<>();
        for (String mutation : bank) {
            sets.add(mutation);
        }

        if (!sets.contains(end)) {
            return -1;
        }

        int step = 1;
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        char[] letter = new char[]{'A', 'C', 'G', 'T'};
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                String cur = queue.poll();
                for (int j = 0; j < cur.length(); j++) {
                    for (int k = 0; k < 4; k++) {
                        if (cur.charAt(j) != letter[k]) {
                            StringBuffer sb = new StringBuffer(cur);
                            sb.setCharAt(j, letter[k]);
                            if (sets.contains(sb.toString()) && !visited.contains(sb.toString())) {
                                if (sb.toString().equals(end)) {
                                    return step;
                                }
                                visited.add(sb.toString());
                                queue.offer(sb.toString());
                            }
                        }
                    }
                }
            }
            step++;
        }

        return -1;
    }

    public static void main(String[] args) {
        // 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
        // 输出：1
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"};
        System.out.println(minMutation1(start, end, bank));
    }
}
