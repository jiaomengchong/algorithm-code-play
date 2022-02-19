package com.jmc.algorithm.greatOffer.class30;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角形
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/20 16:08
 */
public class Problem_0118_PascalsTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            // 1
            // 1 1
            // 1 2 1
            // 1 3 3 1
            list.add(1);
            for (int j = 1; j < i; j++) {
                List<Integer> preList = ans.get(i - 1);
                list.add(preList.get(j) + preList.get(j - 1));
            }
            if (list.size() - 1 != i) {
                list.add(1);
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
