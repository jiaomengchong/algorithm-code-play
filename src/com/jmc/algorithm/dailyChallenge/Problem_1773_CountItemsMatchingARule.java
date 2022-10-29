package com.jmc.algorithm.dailyChallenge;

import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/count-items-matching-a-rule/
 */
public class Problem_1773_CountItemsMatchingARule {
    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ans = 0;
        int index = ruleKey.equals("type") ? 0 : ruleKey.equals("color") ? 1 : 2;
        for (List<String> item : items) {
            ans += item.get(index).equals(ruleValue) ? 1 : 0;
        }
        return ans;
    }
}
