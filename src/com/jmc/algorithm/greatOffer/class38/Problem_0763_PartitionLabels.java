package com.jmc.algorithm.greatOffer.class38;

import java.util.ArrayList;
import java.util.List;

/**
 * 划分字母区间
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/9 10:24
 **/
public class Problem_0763_PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        int N = s.length();
        List<Integer> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        int[] far = new int[26];
        // 先统计出每个字符能到达的最远位置
        for (int i = 0; i < N; i++) {
            far[str[i] - 'a'] = i;
        }

        int L = 0;
        int R = far[str[0] - 'a'];
        for (int i = 0; i < N; i++) {
            if (i > R) {
                ans.add(R - L + 1);
                L = i;
            }
            R = Math.max(R, far[str[i] - 'a']);
        }
        ans.add(R - L + 1);

        return ans;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }
}
