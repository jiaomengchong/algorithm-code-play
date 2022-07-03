package com.jmc.algorithm.dailyChallenge;

import java.util.Map;
import java.util.TreeMap;

/**
 * 测试链接：https://leetcode.cn/problems/next-greater-element-iii/
 */
public class Problem_0556_NextGreaterElementiii {
    public static int nextGreaterElement(int n) {
        boolean exist = false;
        StringBuffer sb = new StringBuffer();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int index = 0;
        int swapIndex = 0;
        while(n != 0) {
            Integer ceilingKey = treeMap.ceilingKey((n % 10) + 1);
            if (!exist && ceilingKey != null && ceilingKey != n % 10) {
                swapIndex = treeMap.get(ceilingKey);
                sb.deleteCharAt(swapIndex);
                sb.append(n % 10);
                sb.append(ceilingKey);
                swapIndex = index;
                exist = true;
            } else {
                sb.append(n % 10);
            }
            treeMap.put(n % 10, index++);
            n /= 10;
        }
        if (exist) {
            TreeMap<Character, Integer> countMap = new TreeMap<>();
            int count = 0;
            for (int i = 0; i < swapIndex; i++) {
                countMap.put(sb.charAt(i), countMap.getOrDefault(sb.charAt(i), 0) + 1);
                count++;
            }
            for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
                for (int i = entry.getValue(); i > 0; i--) {
                    sb.setCharAt(--count, entry.getKey());
                }
            }
        }
        long ans = Long.parseLong(sb.reverse().toString());
        return exist ? (int) (ans > Integer.MAX_VALUE ? -1 : ans) : -1;
    }

    public static int nextGreaterElement1(int n) {
        char[] str = String.valueOf(n).toCharArray();
        // 1 2 3
        int firstLessIndex = -1;
        int N = str.length;
        for (int i = N - 2; i >= 0; i--) {
            if (str[i] < str[i + 1]) {
                firstLessIndex = i;
                break;
            }
        }

        if (firstLessIndex < 0) {
            return -1;
        }

        int needIndex = N - 1;
        for (int i = N - 1; i > firstLessIndex; i--) {
            if (str[i] > str[firstLessIndex]) {
                needIndex = i;
                break;
            }
        }

        swap(str, firstLessIndex, needIndex);
        reverse(str, firstLessIndex + 1, N - 1);
        String s = new String(str);
        long ans = Long.valueOf(s);
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    private static void reverse(char[] str, int start, int end) {
        while (start < end) {
            swap(str, start ++, end--);
        }
    }

    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        int n = 1243;
        System.out.println(nextGreaterElement(n));
        System.out.println(nextGreaterElement1(n));
    }
}
