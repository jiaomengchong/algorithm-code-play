package com.jmc.algorithm.dailyChallenge;

import java.util.*;

public class Problem_0710_RandomPickWithBlacklist {
    static class Solution {
        Map<Integer, Integer> indexMap;
        Random random;
        int boundary;

        public Solution(int n, int[] blacklist) {
            random = new Random();
            indexMap = new HashMap<>();
            int size = blacklist.length;
            boundary = n - size;
            Set<Integer> sets = new HashSet<>();
            for (int black : blacklist) {
                if (black >= boundary) {
                    sets.add(black);
                }
            }

            int window = boundary;
            for (int black : blacklist) {
                if (black < boundary) {
                    while (sets.contains(window)) {
                        window++;
                    }
                    indexMap.put(black, window++);
                }
            }
        }

        public int pick() {
            int pick = random.nextInt(boundary);
            return indexMap.getOrDefault(pick, pick);
        }
    }

    public static void main(String[] args) {
        int n = 7;
        int[] blackList = new int[]{2, 3, 5};
        Solution solution = new Solution(n, blackList);
        for (int i = 0; i < 7; i++) {
            System.out.println(solution.pick());
        }
    }
}
