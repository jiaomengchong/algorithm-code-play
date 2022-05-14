package com.jmc.algorithm.jjb.class19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/5 16:14
 */
public class Code03_StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        if (target == null || target.length() == 0 || stickers == null || stickers.length == 0) {
            return 0;
        }

        int process = process1(target, stickers);

        return process == Integer.MAX_VALUE ? -1 : process;
    }

    private static int process1(String target, String[] arr) {
        if (target.length() == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (String first : arr) {
            String rest = minusTarget(target, first);
            if (rest.length() != target.length()) {
                ans = Math.min(ans, process1(rest, arr));
            }
        }

        return ans + (ans == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minusTarget(String target, String first) {
        char[] chars1 = target.toCharArray();
        char[] chars2 = first.toCharArray();
        int[] counts = new int[26];
        for (char ch : chars1) {
            counts[ch - 'a']++;
        }
        for (char ch : chars2) {
            counts[ch - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    builder.append((char) ('a' + i));
                }
            }
        }

        return builder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        if (target == null || target.length() == 0 || stickers == null || stickers.length == 0) {
            return 0;
        }

        int N = stickers.length;
        int[][] stickersCounts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char ch : chars) {
                stickersCounts[i][ch - 'a']++;
            }
        }

        Map<String, Integer> dp = new HashMap<>();
        int process = process2(stickersCounts, target, dp);
        return process == Integer.MAX_VALUE ? -1 : process;
    }

    private static int process2(int[][] stickersCounts, String target, Map<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }

        if (target.length() == 0) {
            return 0;
        }

        char[] targetChars = target.toCharArray();
        int[] targetCounts = new int[26];
        for (char ch : targetChars) {
            targetCounts[ch - 'a']++;
        }

        int N = stickersCounts.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] stickersCount = stickersCounts[i];
            if (stickersCount[targetChars[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (targetCounts[j] > 0) {
                        int nums = targetCounts[j] - stickersCount[j];
                        if (nums > 0) {
                            for (int k = 0; k < nums; k++) {
                                builder.append((char) (j + 'a'));
                            }
                        }
                    }
                }
                String rest = builder.toString();
                res = Math.min(res, process2(stickersCounts, rest, dp));
            }
        }

        res = res + (res == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, res);
        return res;
    }

    public static void main(String[] args) {
        String target = "travelbell";
        String[] arr = {"heavy", "claim", "seven", "set", "had", "it", "dead", "jump", "design", "question", "sugar", "dress", "any", "special", "ground", "huge", "use", "busy", "prove", "there", "lone", "window", "trip", "also", "hot", "choose", "tie", "several", "be", "that", "corn", "after", "excite", "insect", "cat", "cook", "glad", "like", "wont", "gray", "especially", "level", "when", "cover", "ocean", "try", "clean", "property", "root", "wing"};
//        System.out.println(minStickers2(arr, target));
        System.out.println(minStickers2(arr, target));

        int[] counts = new int[26];
        char[] targetChars = target.toCharArray();
        int N = targetChars.length;
        for (char ch : targetChars) {
            counts[ch - 'a']++;
        }
        //System.out.println(Arrays.toString(counts));

        int[][] stickerCounts = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            char[] stickerChars = arr[i].toCharArray();
            for (char ch : stickerChars) {
                stickerCounts[i][ch - 'a']++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            //System.out.println(Arrays.toString(stickerCounts[i]));
        }

        for (int i = 0; i < arr.length; i++) {
            int[] stickerCount = stickerCounts[i];
            //System.out.println(stickerCount[targetChars[0] - 'a']);
        }
    }
}
