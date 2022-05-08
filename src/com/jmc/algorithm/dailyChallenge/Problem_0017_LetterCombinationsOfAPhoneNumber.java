package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/5 15:49
 **/
public class Problem_0017_LetterCombinationsOfAPhoneNumber {
    static Map<Character, String> phoneMap = new HashMap<>();
    static {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        dfs(digits, 0, ans, sb);
        return ans;
    }

    private void dfs(String digits, int index, List<String> ans, StringBuffer sb) {
        if (index == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        String phoneNum = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < phoneNum.length(); i++) {
            sb.append(phoneNum.charAt(i));
            dfs(digits, index + 1, ans, sb);
            sb.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        String digits = "234";
        Problem_0017_LetterCombinationsOfAPhoneNumber test = new Problem_0017_LetterCombinationsOfAPhoneNumber();
        System.out.println(test.letterCombinations(digits));
    }
}
