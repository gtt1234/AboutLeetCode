package com.tt.leetcode.easy.array;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class PlusOne {
    //按照先算出来值再除以10的方法在值很大时容易溢出
    //尝试用逻辑加的方式
    public int[] plusOne(int[] digits) {
        long sum = 0;
        int pow = digits.length - 1;
        for (int i = 0; i < digits.length; i++) {
            sum = sum + (long) (digits[i] * Math.pow(10, pow));
            pow--;
        }
        sum++;
        int[] plusOne;
        long l = sum / (long) Math.pow(10, digits.length);
        if (l != 0) {
            plusOne = new int[digits.length + 1];
        } else {
            plusOne = new int[digits.length];
        }
        int j = plusOne.length - 1;
        while (j >= 0) {
            plusOne[j] = (int) (sum % 10);
            sum = sum / 10;
            j--;
        }
        return plusOne;
    }

    //就按照加法的方式来计算
    public int[] plusOneBetter(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    public static void main(String[] args) {
        int[] plusone = new PlusOne().plusOne(new int[]{4, 2, 1, 1});
        System.out.println(plusone);
        int[] plusOne1 = new PlusOne().plusOneBetter(new int[]{7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 6});
        int[] plusOne2 = new PlusOne().plusOneBetter(new int[]{4, 2, 1, 1});
        System.out.println(plusOne1);

    }
}
