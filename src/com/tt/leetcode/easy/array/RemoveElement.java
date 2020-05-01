package com.tt.leetcode.easy.array;


public class RemoveElement {

    // nums = [3,2,2,3], val = 3,
    //设置两个指针，i=len-1,j=len-1
    //从后往前遍历，j依次减1，直至j=0,遇到num[j]=val的，num[j]与num[i]互换，i--;
    //移除后数组的新长度为i+1
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int i = nums.length - 1;
        int j = i;
        for (; j >= 0; j--) {
            if (nums[j] == val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i--;
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveElement().removeElement(new int[]{3,2,2,3},3));
    }
}
