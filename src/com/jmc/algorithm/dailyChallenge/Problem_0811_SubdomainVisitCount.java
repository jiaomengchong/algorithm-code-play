package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/subdomain-visit-count/
 */
public class Problem_0811_SubdomainVisitCount {
    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> ans = new ArrayList<>();
        // 输入：cpdomains = ["9001 discuss.leetcode.com"]
        // 输出：["9001 leetcode.com","9001
        int N = cpdomains.length;
        Map<String, Integer> cntMap = new HashMap<>();
        for (String domains : cpdomains) {
            String[] cntArray = domains.split(" ");
            Integer cnt = Integer.valueOf(cntArray[0]);
            String domain = cntArray[1];
            String[] domArray = domain.split("\\.");
            cntMap.put(domain, cntMap.getOrDefault(domain, 0) + cnt);
            int pre = domArray[0].length() + 1;
            for (int i = 1; i < domArray.length; i++) {
                cntMap.put(domain.substring(pre), cntMap.getOrDefault(domain.substring(pre), 0) + cnt);
                pre += domArray[i].length() + 1;
            }
        }

        for (Map.Entry entry : cntMap.entrySet()) {
            ans.add(String.format("%s %s", entry.getValue(), entry.getKey()));
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] cpdomains = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subdomainVisits(cpdomains));
    }
}
