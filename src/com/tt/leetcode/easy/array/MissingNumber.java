package com.tt.leetcode.easy.array;

/**
 * @Description 丢失的数字
 * @Author gantt
 * @Date 2021/1/31 12:12
 */
public class MissingNumber {

    //高斯公式
    public int missingNumber(int[] nums) {
        int res = (0 + nums.length) * (nums.length + 1) / 2;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return res - sum;
    }


    //位运算；对一个数进行两次完全相同的异或运算会得到原来的数；
    //我们可以先得到【0，n】和数组中各出现一次，因此异或后得到0；而
    //缺失的数只再【0，n】中出现了一次，再数组中没有出现；因此最终的异或结果即为这个缺失的数字
    public int missingNumberBetter(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    public static void main(String[] args) {
        MissingNumber missingNumber = new MissingNumber();

        System.out.println(missingNumber.missingNumber(new int[]{3, 0, 1}));
        System.out.println(missingNumber.missingNumber(new int[]{0, 1}));
        System.out.println(missingNumber.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(missingNumber.missingNumber(new int[]{0}));
    }
}
