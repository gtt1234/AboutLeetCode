package com.tt.leetcode.easy.Integer;


/**
 * @Description 第一个错误的版本
 * @Author gantt
 * @Date 2021/1/31 15:16
 */
public class VersionControl {

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) /2;
            if(isBadVersion(mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int i) {
        return true;
    }
}
