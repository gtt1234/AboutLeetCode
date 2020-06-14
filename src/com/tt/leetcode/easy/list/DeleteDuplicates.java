package com.tt.leetcode.easy.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        Map<Integer, Integer> map = new HashMap<>();
        ListNode temp = head.next;
        ListNode pre = head;
        map.put(head.val, 0);
        //temp用来遍历,pre用来连接有效的点
        //因为这是一个有序的链表,找出所有不相等的节点连起来
        while (temp != null) {
            if (map.get(temp.val) == null) {
                map.put(temp.val, 0);
                pre.next = temp;
                pre = pre.next;
            }
            temp = temp.next;
        }
        //最后一个有效节点的下一位一定是null
        pre.next = null;
        return head;
    }

    /**
     * 这是一个简单的问题，仅测试你操作列表的结点指针的能力。由于输入的列表已排序，
     * 因此我们可以通过将结点的值与它之后的结点进行比较来确定它是否为重复结点。如果它是重复的，
     * 我们更改当前结点的 next 指针，以便它跳过下一个结点并直接指向下一个结点之后的结点。
     */
    //看看官方解答多简单
    public ListNode deleteDuplicatesBetter(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/solution/dong-hua-yan-shi-83-shan-chu-pai-xu-lian-biao-zhon/
     * 一次遍历的方式，需要用a和b两个指针，然后b指针不断往前走，如果a指针和b指针的元素相等则啥都不做；
     * 如果a指针和b指针的元素不等，则a指针也往前走一位，并将b指针的值赋给a指针。
     *
     */
    public ListNode deleteDuplicatesBetter1(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode i = head;
        ListNode j = head;
        while(j!=null) {
            //如果i不等于j，则i前进一位，然后将j的值赋给i
            //请配合动画演示理解
            if(i.val!=j.val) {
                i = i.next;
                i.val = j.val;
            }
            //不管i是否等于j，j每次都前进一位
            j = j.next;
        }
        i.next = null;
        return head;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        ListNode id = new DeleteDuplicates().deleteDuplicates(head);
        System.out.println("");
    }
}
