package com.tt.leetcode.easy.Tree;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class MinDepth {

    /**
     * 思路:可以参考做的树的最大深度类似
     * 出现一个问题是这里说的最小深度是从根节点到最近的叶子节点的最短路径的上的节点数量
     * 考虑一种情况就是如果没有左节点或者没有右节点,此时的长度应该是到左节点的高度或者到右节点的高度
     * 而不是取左子树和右子树其中较小的
     *  *     3
     *  *    /
     *  *   9
     *  最小深度应该是2
     *  时间复杂度：我们访问每个节点一次，时间复杂度为 O(N)O(N) ，其中 NN 是节点个数。
     */
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left!=0 && right!=0){
            return Math.min(left,right) +1;
        }

        if(left==0){
            return right +1;
        }else{
            return left + 1;
        }
    }

    //深度优先搜索方法的缺陷是所有节点都必须访问到，以保证能够找到最小深度。因此复杂度是O(N) 。
    //一个优化的方法是利用广度优先搜索，我们按照树的层去迭代，第一个访问到的叶子就是最小深度的节点，这样就不用遍历所有的节点了。
    public int minDepthBetter(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            stack.add(new Pair(root, 1));
        }

        int current_depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }

}
