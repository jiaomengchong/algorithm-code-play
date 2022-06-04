package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/unique-email-addresses/
 */
public class Problem_0929_UniqueEmailAddresses {
    public static int numUniqueEmails(String[] emails) {
        // 输入：emails = ["test.email+alex@leetcode.com",
        //                "test.e.mail+bob.cathy@leetcode.com",
        //                "testemail+david@lee.tcode.com"]
        //输出：2
        //解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
        Set<String> sets = new HashSet<>();
        int N = emails.length;
        for (int i = 0; i < N; i++) {
            String email = process(emails[i]);
            if (!sets.contains(email)) {
                sets.add(email);
            }
        }
        return sets.size();
    }

    private static String process(String email) {
        String ret;
        String[] split = email.split("@");
        String[] localName = split[0].split("\\+");
        ret = String.format("%s@%s", localName[0].replace(".", ""), split[1]);
        return ret;
    }

    public static void main(String[] args) {
        String[] emails = new String[]{"test.email+alex@leetcode.com","test.email.leet+alex@code.com"};
        System.out.println(numUniqueEmails(emails));
    }
}
