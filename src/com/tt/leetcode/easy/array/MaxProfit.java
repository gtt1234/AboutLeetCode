package com.tt.leetcode.easy.array;

public class MaxProfit {

    public int maxProfit(int[] prices) {
        int[] arr = new int[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            arr[i] = prices[i + 1] - prices[i];
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sum = sum + arr[i];
            }
        }
        return sum;
    }

    //这样写更简单
    public int maxProfitBetter(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

}
