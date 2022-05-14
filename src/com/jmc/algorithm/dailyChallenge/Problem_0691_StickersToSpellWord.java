package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

public class Problem_0691_StickersToSpellWord {
    public static int minStickers(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (String ticker : stickers) {
            String minus = minus(target, ticker);
            if (minus.length() != target.length()) {
                int p = process1(stickers, minus);
                p += p != Integer.MAX_VALUE ? 1 : 0;
                ans = Math.min(ans, p);
            }
        }

        return ans;
    }

    private static String minus(String target, String ticker) {
        char[] str1 = target.toCharArray();
        char[] str2 = ticker.toCharArray();
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        StringBuffer sb = new StringBuffer();
        // 词频统计
        for (char ch : str1) {
            count1[ch - 'a']++;
        }
        for (char ch : str2) {
            count2[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count1[i] - count2[i] > 0) {
                for (int j = 0; j < count1[i] - count2[i]; j++) {
                    sb.append((char) ('a' + i));
                }
            }
        }
        return sb.toString();
    }

    public static int minStickers2(String[] tickers, String target) {
        int[][] counts = new int[tickers.length][26];
        // 词频统计
        for (int i = 0; i < tickers.length; i++) {
            char[] ticker = tickers[i].toCharArray();
            for (char ch : ticker) {
                counts[i][ch - 'a']++;
            }
        }

        Map<String, Integer> dp = new HashMap<>();
        int ans = process2(counts, target, dp);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private static int process2(int[][] tickers, String target, Map<String, Integer> dp) {
        if (target.length() == 0) {
            return 0;
        }

        if (dp.containsKey(target)) {
            return dp.get(target);
        }

        int[] tc = new int[26];
        for (char ch : target.toCharArray()) {
            tc[ch - 'a']++;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < tickers.length; i++) {
            int[] ticker = tickers[i];
            // 重要剪枝[找包含target第一个字符的贴纸]
            if (ticker[target.charAt(0) - 'a'] > 0) {
                String rest = minus2(ticker, tc);
                int p = process2(tickers, rest, dp);
                p += p != Integer.MAX_VALUE ? 1 : 0;
                ans = Math.min(ans, p);
            }
        }

        dp.put(target, ans);
        return ans;
    }

    private static String minus2(int[] ticker, int[] target) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            int cnt = target[i] - ticker[i];
            if (cnt > 0) {
                for (int j = 0; j < cnt; j++) {
                    sb.append((char)('a' + i));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // ["notice","possible"]
        //"basicbasic"
        String[] tickers = new String[]{"notice","possible"};
        String target = "basicbasic";
        System.out.println(minStickers(tickers, target));
        System.out.println(minStickers2(tickers, target));
    }
}
