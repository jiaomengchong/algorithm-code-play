package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jmc
 * @Description
 * @Date 2022/7/22 18:51
 **/
public class Problem_1352_ProductOfTheLastKNumbers {
    static class ProductOfNumbers1 {
        List<Integer> nums;

        public ProductOfNumbers1() {
            nums = new ArrayList<>();
        }

        public void add(int num) {
            nums.add(num);
        }

        public int getProduct(int k) {
            int ans = 1;
            for (int i = nums.size() - 1; i > nums.size() - 1 - k; i--) {
                if (nums.get(i) == 0) {
                    return 0;
                }
                ans *= nums.get(i);
            }
            return ans;
        }
    }

    static class ProductOfNumbers {
        int[] product;
        int size;

        public ProductOfNumbers() {
            product = new int[40001];
            size = 0;
            product[0] = 1;
        }

        public void add(int num) {
            if (num == 0) {
                size = 0;
            } else {
                product[++size] = num;
                product[size] *= product[size - 1];
            }
        }

        public int getProduct(int k) {
            if (k > size) {
                return 0;
            }
            return product[size] / product[size - k];
        }
    }

    public static void main(String[] args) {
        ProductOfNumbers product = new ProductOfNumbers();
        product.add(3);
        product.add(0);
        product.add(2);
        product.add(5);
        product.add(4);
        int ans = product.getProduct(4);
        System.out.println(ans);
    }
}
