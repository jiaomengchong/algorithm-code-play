package com.jmc.algorithm.greatOffer.class23;

import java.util.*;

/**
 * 超级水王问题 [1, 2, 3, 1, 1, 2, 1],1就是水王
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/20 14:33
 */
public class Code04_FindKMajority {
    public static void printHalfMajority(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("no such number!");
            return;
        }

        int N = arr.length;
        int candidate = 0;
        int help = 0;
        // 找出水王的候选人，不一定是水王
        for (int i = 0; i < N; i++) {
            if (help == 0) {
                candidate = arr[i];
                help = 1;
            } else if (candidate == arr[i]) {
                help++;
            } else {
                help--;
            }
        }

        if (help == 0) {
            System.out.println("no such number!");
            return;
        }

        help = 0;
        for (int i = 0; i < N; i++) {
            if (candidate == arr[i]) {
                help++;
            }
        }
        if (help > (N >> 1)) {
            System.out.println(candidate);
        } else {
            System.out.println("no such number!");
        }
    }

    public static void printKMajority(int[] arr, int K) {
        if (arr == null || arr.length < K) {
            System.out.println("no such number!");
            return;
        }

        // [1, 2, 3, 1, 1, 2, 1] K=4,表示最多有3个数，即K-1，过程如下：
        // map.put(1, 1)
        // map.put(2, 1)
        // map.put(3, 1)
        // map.put(1, 2)
        // map.put(1, 3)
        // map.put(2, 2)
        // map.put(1, 4)
        int N = arr.length;
        HashMap<Integer, Integer> candidateMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (candidateMap.containsKey(arr[i])) {
                candidateMap.put(arr[i], candidateMap.get(arr[i]) + 1);
            } else {
                if (candidateMap.size() == K - 1) {
                    allCandidateMinusOne(candidateMap);
                } else {
                    candidateMap.put(arr[i], 1);
                }
            }
        }

        HashMap<Integer, Integer> realMap = calculateCandidate(arr, candidateMap);
        boolean isExist = false;
        for (Map.Entry<Integer, Integer> sets: realMap.entrySet()) {
            if (sets.getValue() > N / K) {
                System.out.print(sets.getKey() + " ");
                isExist = true;
            }
        }
        System.out.println(isExist ? "" : "no such number!");
    }

    private static HashMap<Integer, Integer> calculateCandidate(int[] arr, HashMap<Integer, Integer> candidateMap) {
        HashMap<Integer, Integer> realMap = new HashMap<>(candidateMap.size());
        for (int i = 0; i < arr.length; i++) {
            if (candidateMap.containsKey(arr[i])) {
                realMap.put(arr[i], realMap.getOrDefault(arr[i], 0) + 1);
            }
        }
        return realMap;
    }

    private static void allCandidateMinusOne(HashMap<Integer, Integer> candidateMap) {
        List<Integer> removeList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : candidateMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            candidateMap.put(key, value - 1);
        }

        if (removeList.size() != 0) {
            for (Integer key : removeList) {
                candidateMap.remove(key);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1, 1, 2, 1};
        printHalfMajority(arr);
        printKMajority(arr, 2);
    }
}
