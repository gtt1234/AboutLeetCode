package com.tt.leetcode.easy.array;


/**
 * @Description 移动零
 * @Author gantt
 * @Date 2021/1/31 15:42
 */
public class MoveZero {
    //0,1,3,0,12
    //将所有0移动到数组的末尾；同时保持非零元素的相对顺序
    public void moveZeroes(int[] nums) {
        int countZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                countZero++;
            } else {
                //如果不为0且前面没有0；继续遍历
                if (countZero == 0) {
                    continue;
                } else {
                    int tmp = nums[i];
                    nums[i] = nums[i - countZero];
                    nums[i - countZero] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,12,0,0,4,0};
        new MoveZero().moveZeroes(nums);
        for (int i : nums){
            System.out.print(i+" ");
        }

    }
}
