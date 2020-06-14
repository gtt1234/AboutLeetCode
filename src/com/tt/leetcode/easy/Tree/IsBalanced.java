package com.tt.leetcode.easy.Tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。

 */
public class IsBalanced {
    /**
     * 肯定用递归做,思考:每个节点的左右子树的高度差不超过1,从根节点出发,根节点的左子树的长度与右子树的高度之差绝对值<=1
     * 在之前的题中有计算树的深度,
     * 同理根节点的左节点的左子树与右子树高度之差的绝对值和根节点的右节点的左子树和右子树高度之差的绝对值都<=1;
     * 这个地方用递归
     * 自顶向下的递归,我的思路跟官方的方法一一样,存在的问题是计算子树高度存在大量的冗余
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        boolean isBalanced = Math.abs(depth(root.left)-depth(root.right))<=1;
        return isBalanced && isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(TreeNode root){
        if(root==null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(left,right) +1;
    }


    //方法一计算 height 存在大量冗余。每次调用 height 时，要同时计算其子树高度。
    // 但是自底向上计算，每个子树的高度只会计算一次。可以递归先计算当前节点的子节点高度，然后再通过子节点高度判断当前节点是否平衡，从而消除冗余。

    // Utility class to store information from recursive calls
    final class TreeInfo {
        public final int height;
        public final boolean balanced;

        public TreeInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    // Return whether or not the tree at root is balanced while also storing
    // the tree's height in a reference variable.
    //使用与方法一中定义的 \texttt{height}height 方法。自底向上与自顶向下的逻辑相反，首先判断子树是否平衡，然后比较子树高度判断父节点是否平衡
    private TreeInfo isBalancedTreeHelper(TreeNode root) {
        // An empty tree is balanced and has height = -1
        if (root == null) {
            return new TreeInfo(-1, true);
        }

        // Check subtrees to see if they are balanced.
        TreeInfo left = isBalancedTreeHelper(root.left);
        //判断左子树是否平衡
        if (!left.balanced) {
            return new TreeInfo(-1, false);
        }
        TreeInfo right = isBalancedTreeHelper(root.right);
        //判断右子树是否平衡
        if (!right.balanced) {
            return new TreeInfo(-1, false);
        }

        // Use the height obtained from the recursive calls to
        // determine if the current node is also balanced.
        //左子树和右子树都平衡再比较左右子树高度差判断父节点是否平衡
        if (Math.abs(left.height - right.height) < 2) {
            return new TreeInfo(Math.max(left.height, right.height) + 1, true);
        }
        return new TreeInfo(-1, false);
    }

    public boolean isBalancedBetter(TreeNode root) {
        return isBalancedTreeHelper(root).balanced;
    }

}
