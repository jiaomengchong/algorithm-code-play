package com.jmc.algorithm.greatOffer.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/1 15:49
 */
public class Code02_RemoveInvalidParentheses {
    public static List<String> removeInvalidParentheses(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        List<String> ans = new ArrayList<>();
        remove(str, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    /**
     * ( ) ( ) ) ( )
     * 0 1 2 3 4 5 6
     * checkIndex和deleteIndex从0开始检查，检查到4的时候违规[删除4之前的右括号]，然后直接return[后续的交给子过程处理]
     * 删除位置1的右括号
     * ( ( ) ) ( )
     * 0 1 2 3 4 5 6
     * ( ) ( ) ( )
     * 0 1 2 3 4 5 6
     * @param s
     * @param ans
     * @param checkIndex
     * @param deleteIndex
     * @param par
     */
    private static void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
        for (int i = checkIndex, count = 0; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            if (count < 0) {
                for (int j = deleteIndex; j <= i; j++) {
                    if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                    }
                }
                return;
            }
        }

        String reversed = new StringBuffer(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reversed);
        }
    }


    public static void main(String[] args) {
        String s = "(((k()((";
        System.out.println(removeInvalidParentheses(s));

        String str = "123";
        System.out.println(new StringBuffer(s).reverse().toString());
        System.out.println(new StringBuffer(str).reverse().toString());
    }
}
