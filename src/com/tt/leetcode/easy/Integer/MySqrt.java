package com.tt.leetcode.easy.Integer;

public class MySqrt {
    /**
     * 当 x ≥2 时，它的整数平方根一定小于 x / 2 且大于 0，即 0 < a < x / 2。
     * 由于 a 一定是整数，此问题可以转换成在有序整数集中寻找一个特定值，因此可以使用二分查找。
     */
    public int mySqrt(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }

}
