package com.tt.leetcode.easy.array;

public class Rotate {
    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */

    //方法 1：暴力
    //最简单的方法是旋转 k 次，每次将数组旋转 1 个元素。
    public void rotate1(int[] nums, int k) {
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }


    //我的想法,应该是解法里的环状替换,但是想不出来怎么解决在没有遍历所有数字的情况下回到出发点这个问题
    //例如 [1,2,3,4] 移动k=2;
    public void rotate(int[] nums, int k) {
        int t = nums[0], temp = 0, index = 0;
        int length = nums.length;
        int count = 0;
        do {
            index = (index + k) > (length - 1) ? (index + k - length) : (index + k);
            temp = t;
            t = nums[index];
            nums[index] = temp;
            count++;
        } while (count <= length);
    }

    //环状替换
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            //继续下一轮替换的解决方法
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }


    //巧方法,反转法
    //这个方法基于这个事实：当我们旋转数组 k 次， k\%nk%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
    //在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-kn−k 个元素，就能得到想要的结果。
    //假设 n=7n=7 且 k=3k=3 。
    //原始数组                  : 1 2 3 4 5 6 7
    //反转所有数字后             : 7 6 5 4 3 2 1
    //反转前 k 个数字后          : 5 6 7 4 3 2 1
    //反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        new Rotate().rotate(nums, 2);
        for (int i : nums) {
            System.out.print(i + "");
        }
    }
}
